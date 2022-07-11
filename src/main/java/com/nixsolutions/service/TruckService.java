package com.nixsolutions.service;

import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import com.nixsolutions.repository.CrudRepository;
import com.nixsolutions.repository.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TruckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckService.class);
    private static final CrudRepository<Truck> REPOSITORY = new TruckRepository();
    private static final Random RANDOM = new Random();

    public List<Truck> create(int count) {
        List<Truck> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Truck truck = new Truck(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    getRandomNumberOfPassengers()
            );
            result.add(truck);
            LOGGER.debug("Created truck {}", truck.getId());
        }
        return result;
    }

    private int getRandomNumberOfPassengers() {
        return RANDOM.nextInt(100, 200);
    }

    public void save(List<Truck> trucks) {
        REPOSITORY.create(trucks);
    }

    public void printAll() {
        for (Object object : REPOSITORY.getAll()) {
            System.out.println(object);
        }
    }

    public boolean update(Truck truck) {
        if (REPOSITORY.getById(truck.getId()) != null) {
            LOGGER.debug("Update auto {}", truck.getId());
        }
        return REPOSITORY.update(truck);
    }

    public boolean delete(String id) {
        if (REPOSITORY.delete(id)) {
            LOGGER.debug("Remove truck {}", id);
            return true;
        }
        return false;
    }

    Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}

