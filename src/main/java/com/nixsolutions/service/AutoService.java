package com.nixsolutions.service;

import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Vehicle;
import com.nixsolutions.repository.AutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AutoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoService.class);
    private static final Random RANDOM = new Random();
    private final AutoRepository autoRepository;

    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public List<Auto> createAndSaveAutos(int count) {
        List<Auto> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Auto auto = new Auto(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    getRandomBody()

            );
            result.add(auto);
            autoRepository.save(auto);
            LOGGER.debug("Created auto {}", auto.getId());
        }
        return result;
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void saveAutos(List<Auto> autos) {
        autoRepository.saveAll(autos);
    }

    public void printAll() {
        for (Auto auto : autoRepository.getAll()) {
            System.out.println(auto);
        }
    }

    private Body getRandomBody() {
        final Body[] values = Body.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean update(Auto auto) {
        if (autoRepository.findById(auto.getId()).isPresent()) {
            LOGGER.debug("Update auto {}", auto.getId());
        }
        return autoRepository.update(auto);
    }

    public boolean delete(String id) {
        if (autoRepository.delete(id)) {
            LOGGER.debug("Remove auto {}", id);
            return true;
        }
        return false;
    }

    public Optional<Auto> findOneById(String id) {
        return id == null ? autoRepository.findById("") : autoRepository.findById(id);
    }

    public void optionalExmaples() {
        final Auto auto = createAndSaveAutos(1).get(0);
        final String id = auto.getId();

//        simpleCheck(id);
        isPresent(id);
        ifPresent(id);
        orElse(id);
        orElseThrow(id);
        or(id);
        orElseGet(id);
        filter(id);
        map(id);
        ifPresentOrElse(id);
    }

    /*private void simpleCheck(String id) { EXAMPLE
        final Auto auto = autoRepository.getById(id);
        if (auto != null) {
            System.out.println(auto.getModel());
        }
    }*/

    private void isPresent(String id) {
        final Optional<Auto> autoOptional1 = autoRepository.findById(id);
        autoOptional1.ifPresent(auto -> System.out.println(auto.getModel()));

        final Optional<Auto> autoOptional2 = autoRepository.findById("123");
        autoOptional2.ifPresent(auto -> System.out.println(auto.getModel()));

        if (autoOptional2.isEmpty()) {
            System.out.println("Auto with id \"123\" not found");
        }
    }

    private void ifPresent(String id) {
        autoRepository.findById(id).ifPresent(auto -> System.out.println(auto.getModel()));

        autoRepository.findById("123").ifPresent(auto -> System.out.println(auto.getModel()));
    }

    private void orElse(String id) {
        final Auto auto1 = autoRepository.findById(id).orElse(createOne());
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        final Auto auto2 = autoRepository.findById("123").orElse(createOne());
        System.out.println(auto2.getModel());
    }

    private void orElseThrow(String id) {
        final Auto auto1 = autoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + id));
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        try {
            final Auto auto2 = autoRepository.findById("123")
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + "123"));
            System.out.println(auto2.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void or(String id) {
        final Optional<Auto> auto1 = autoRepository.findById(id).or(() -> Optional.of(createOne()));
        auto1.ifPresent(auto -> System.out.println(auto.getModel()));

        System.out.println("~".repeat(10));

        final Optional<Auto> auto2 = autoRepository.findById("123").or(() -> Optional.of(createOne()));
        auto2.ifPresent(auto -> System.out.println(auto.getModel()));
    }

    private void orElseGet(String id) {
        final Auto auto1 = autoRepository.findById(id).orElseGet(this::createOne);
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        final Auto auto2 = autoRepository.findById("123").orElseGet(() -> {
            System.out.println("Cannot find auto with id " + "123");
            return createOne();
        });
        System.out.println(auto2.getModel());
    }

    private void filter(String id) {
        autoRepository.findById(id)
                .filter(auto -> !auto.getBodyType().equals(""))
                .ifPresent(auto -> {
                    System.out.println(auto.getModel());
                });

        autoRepository.findById(id)
                .filter(auto -> auto.getBodyType().equals(""))
                .ifPresent(auto -> System.out.println(auto.getModel()));
    }

    private void map(String id) {
        autoRepository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

    private void ifPresentOrElse(String id) {
        autoRepository.findById(id).ifPresentOrElse(
                auto -> System.out.println(auto.getModel()),
                () -> System.out.println("Cannot find auto with id " + "123")
        );

        autoRepository.findById("123").ifPresentOrElse(
                auto -> System.out.println(auto.getModel()),
                () -> System.out.println("Cannot find auto with id " + "123")
        );
    }

    private Auto createOne() {
        return new Auto(
                "Model new",
                getRandomManufacturer(),
                BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                getRandomBody()
        );
    }

}
