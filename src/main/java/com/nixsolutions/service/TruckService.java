package com.nixsolutions.service;

import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import com.nixsolutions.model.Vehicle;
import com.nixsolutions.repository.CrudRepository;
import com.nixsolutions.repository.TruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TruckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckService.class);
    private final CrudRepository<Truck> truckRepository;
    private static final Random RANDOM = new Random();

    public TruckService(TruckRepository truckRepository){
        this.truckRepository=truckRepository;
    }

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

    public void save(List<Truck> trucks) {
        truckRepository.saveAll(trucks);
    }

    public void printAll() {
        for (Object object : truckRepository.getAll()) {
            System.out.println(object);
        }
    }

    //orElseThrow
    public boolean update(Truck truck) {
        Optional.ofNullable(truck).orElseThrow(()-> new IllegalArgumentException("vehicle is null"));
        LOGGER.debug("Update vehicle {}", truck.getId());
        return truckRepository.update(truck);
    }

    //orElseThrow
    public boolean delete(String id) {
        Optional.ofNullable(id).orElseThrow(()-> new IllegalArgumentException("id is null"));
        if (truckRepository.delete(id)) {
            LOGGER.debug("Remove vehicle {}", id);
            return true;
        }
        return false;
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    //map
    //or
    public String getModelValueById(String id) {
        return truckRepository.findById(id)
                .map(Vehicle::getModel)
                .or(()-> Optional.of("NONE")).get();
    }

    //map
    //orElse
    public String getManufacturerValueById(String id) {
        return truckRepository.findById(id)
                .map(Vehicle::getManufacturer)
                .map(String::valueOf)
                .orElse("NONE");
    }

    //map
    //orElseGet
    public String getPriceValueById(String id) {
        return truckRepository.findById(id)
                .map(Vehicle::getPrice)
                .map(String::valueOf)
                .orElseGet(()->String.valueOf(new BigDecimal(-1)));
    }
}
