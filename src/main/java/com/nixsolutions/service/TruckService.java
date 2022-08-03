package com.nixsolutions.service;

import com.nixsolutions.model.vehicle.Truck;
import com.nixsolutions.repository.CrudRepository;
import com.nixsolutions.repository.TruckRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class TruckService extends VehicleService<Truck>{
    public static TruckService instance;

    private TruckService(CrudRepository<Truck> repository) {
        super(repository);
    }

    public static TruckService getInstance(){
        if (instance == null) {
            instance=new TruckService(TruckRepository.getInstance());
        }
        return instance;
    }

    @Override
    public List<Truck> create(int count) {
        List<Truck> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Truck truck = new Truck(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    getRandomTransportedWeight()
            );
            result.add(truck);
            LOGGER.debug("Created vehicle {}", truck.getId());
        }
        return result;
    }

    protected int getRandomTransportedWeight() {
        return RANDOM.nextInt(100, 200);
    }
}
