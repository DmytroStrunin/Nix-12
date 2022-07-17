package com.nixsolutions.service;

import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Vehicle;
import com.nixsolutions.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public void save(List<T> trucks) {
        repository.saveAll(trucks);
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

    public boolean delete(String id) {
        Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id is null"));
        if (repository.delete(id)) {
            LOGGER.debug("Remove vehicle {}", id);
            return true;
        }
        return false;
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
}
