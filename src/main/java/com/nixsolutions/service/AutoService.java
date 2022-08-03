package com.nixsolutions.service;

import com.nixsolutions.model.vehicle.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.vehicle.Vehicle;
import com.nixsolutions.repository.AutoRepository;
import com.nixsolutions.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AutoService extends VehicleService<Auto>{
    private static AutoService instance;

    public AutoService(CrudRepository<Auto> repository) {
        super(repository);
    }

    public static AutoService getInstance() {
        if (instance == null) {
            instance= new AutoService(AutoRepository.getInstance());
        }
        return instance;
    }

    @Override
    public List<Auto> create(int count) {
        List<Auto> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Auto auto = new Auto(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    getRandomBody()

            );
            result.add(auto);
            LOGGER.debug("Created auto {}", auto.getId());
        }
        return result;
    }

    public void saveAutos(List<Auto> autos) {
        repository.saveAll(autos);
    }

    private Body getRandomBody() {
        final Body[] values = Body.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void optionalExmaples() {
        final Auto auto = createAndSaveVehicles(1).get(0);
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
        final Optional<Auto> autoOptional1 = repository.findById(id);
        autoOptional1.ifPresent(auto -> System.out.println(auto.getModel()));

        final Optional<Auto> autoOptional2 = repository.findById("123");
        autoOptional2.ifPresent(auto -> System.out.println(auto.getModel()));

        if (autoOptional2.isEmpty()) {
            System.out.println("Auto with id \"123\" not found");
        }
    }

    private void ifPresent(String id) {
        repository.findById(id).ifPresent(auto -> System.out.println(auto.getModel()));

        repository.findById("123").ifPresent(auto -> System.out.println(auto.getModel()));
    }

    private void orElse(String id) {
        final Auto auto1 = repository.findById(id).orElse(createOne());
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        final Auto auto2 = repository.findById("123").orElse(createOne());
        System.out.println(auto2.getModel());
    }

    private void orElseThrow(String id) {
        final Auto auto1 = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + id));
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        try {
            final Auto auto2 = repository.findById("123")
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + "123"));
            System.out.println(auto2.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void or(String id) {
        final Optional<Auto> auto1 = repository.findById(id).or(() -> Optional.of(createOne()));
        auto1.ifPresent(auto -> System.out.println(auto.getModel()));

        System.out.println("~".repeat(10));

        final Optional<Auto> auto2 = repository.findById("123").or(() -> Optional.of(createOne()));
        auto2.ifPresent(auto -> System.out.println(auto.getModel()));
    }

    private void orElseGet(String id) {
        final Auto auto1 = repository.findById(id).orElseGet(this::createOne);
        System.out.println(auto1.getModel());

        System.out.println("~".repeat(10));

        final Auto auto2 = repository.findById("123").orElseGet(() -> {
            System.out.println("Cannot find auto with id " + "123");
            return createOne();
        });
        System.out.println(auto2.getModel());
    }

    private void filter(String id) {
        repository.findById(id)
                .map(Auto::getModel)
                .ifPresent(System.out::println);

        repository.findById(id)
                .ifPresent(auto -> System.out.println(auto.getModel()));
    }

    private void map(String id) {
        repository.findById(id)
                .map(Vehicle::getModel)
                .ifPresent(System.out::println);
    }

    private void ifPresentOrElse(String id) {
        repository.findById(id).ifPresentOrElse(
                auto -> System.out.println(auto.getModel()),
                () -> System.out.println("Cannot find auto with id " + "123")
        );

        repository.findById("123").ifPresentOrElse(
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