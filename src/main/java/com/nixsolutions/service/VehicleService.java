package com.nixsolutions.service;

import com.nixsolutions.model.vehicle.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.vehicle.Vehicle;
import com.nixsolutions.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The {@code VehicleService} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public abstract class VehicleService<T extends Vehicle> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);
    protected final CrudRepository<T> repository;
    protected static final Random RANDOM = new Random();

    public VehicleService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public abstract List<T> create(int count);

    public List<T> createAndSaveVehicles(int count) {
        List<T> result = create(count);
        result.forEach(repository::save);
        return result;
    }

    public void save(List<T> trucks) {
        repository.saveAll(trucks);
    }

    public Optional<T> findOneById(String id) {
        return id == null ? repository.findById("") : repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public void printAll() {
        for (Object object : repository.getAll()) {
            System.out.println(object);
        }
    }

    public boolean update(T t) {
        Optional.ofNullable(t).orElseThrow(() -> new IllegalArgumentException("vehicle is null"));
        LOGGER.debug("Update vehicle {}", t.getId());
        return repository.update(t);
    }

    public T update(int position) {
        return repository.update(position);
    }

    public boolean delete(String id) {
        Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id is null"));
        if (repository.delete(id)) {
            LOGGER.debug("Remove vehicle {}", id);
            return true;
        }
        return false;
    }

    public boolean delete(int position) {
        return repository.delete(position);
    }


    protected Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public String getModelValueById(String id) {
        return repository.findById(id)
                .map(Vehicle::getModel)
                .or(() -> Optional.of("NONE")).get();
    }

    public String getManufacturerValueById(String id) {
        return repository.findById(id)
                .map(Vehicle::getManufacturer)
                .map(String::valueOf)
                .orElse("NONE");
    }

    public String getPriceValueById(String id) {
        return repository.findById(id)
                .map(Vehicle::getPrice)
                .map(String::valueOf)
                .orElseGet(() -> String.valueOf(new BigDecimal(-1)));
    }

    public void printMoreExpensiveCars(BigDecimal x) {
        repository.getAll().stream()
                .filter(vehicle -> x.compareTo(vehicle.getPrice()) > 0)
                .map(Vehicle::getModel)
                .forEach(System.out::println);
    }

    public int getSumVehicles() {
        return repository.getAll().stream()
                .map(Vehicle::getPrice)
                .reduce(BigDecimal::add)
                .map(BigDecimal::intValue)
                .orElse(0);
    }

    public Map<String, String> repositoryToMap() {
        return repository.getAll().stream()
                .distinct()
                .collect(Collectors.toMap(Vehicle::getId, value -> value.getClass().getName()));
    }

    public boolean checkDetailInAllVehicles(String detail) {
        return repository.getAll().stream()
                .map(Vehicle::getDetails)
                .allMatch(details -> details.contains(detail));
    }

    public void printVehiclePriceStatistics(BigDecimal x) {
        final DoubleSummaryStatistics statistics = repository.getAll().stream()
                .map(Vehicle::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .summaryStatistics();
        System.out.println(statistics);
    }

    public boolean checkPriceInAllVehicles(CrudRepository<Vehicle> repository) {
        Predicate<Vehicle> predicate = vehicle -> Objects.nonNull(vehicle.getPrice());
        return repository.getAll().stream()
                .allMatch(predicate);
    }
}
