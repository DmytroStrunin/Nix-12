package com.nixsolutions.service;

import com.nixsolutions.model.Bus;
import com.nixsolutions.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BusService extends VehicleService<Bus> {

    public BusService(CrudRepository<Bus> repository) {
        super(repository);
    }

    @Override
    public List<Bus> create(int count) {
        List<Bus> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Bus bus = new Bus(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    getRandomNumberOfPassengers()
            );
            result.add(bus);
            LOGGER.debug("Created vehicle {}", bus.getId());
        }
        return result;
    }

    protected int getRandomNumberOfPassengers() {
        return RANDOM.nextInt(20, 30);
    }
}









