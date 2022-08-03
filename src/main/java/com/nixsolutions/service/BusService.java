package com.nixsolutions.service;

import com.nixsolutions.model.vehicle.Bus;
import com.nixsolutions.repository.BusRepository;
import com.nixsolutions.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BusService extends VehicleService<Bus> {

    private static BusService instance;

    public BusService(CrudRepository<Bus> repository) {
        super(repository);
    }

    public static BusService getInstance() {
        if (instance == null) {
            instance = new BusService(BusRepository.getInstance());
        }
        return instance;
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









