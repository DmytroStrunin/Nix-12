package com.nixsolutions.service;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Vehicle;
import com.nixsolutions.repository.BusRepository;
import com.nixsolutions.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusService.class);
    private final CrudRepository<Bus> busRepository;
    private static final Random RANDOM = new Random();

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

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

    public void save(List<Bus> buses) {
        busRepository.saveAll(buses);
    }

    public void printAll() {
        for (Object object : busRepository.getAll()) {
            System.out.println(object);
        }
    }

    //orElseThrow
    public boolean update(Bus bus) {
        Optional.ofNullable(bus).orElseThrow(()-> new IllegalArgumentException("vehicle is null"));
        LOGGER.debug("Update vehicle {}", bus.getId());
        return busRepository.update(bus);
    }

    //orElseThrow
    public boolean delete(String id) {
        Optional.ofNullable(id).orElseThrow(()-> new IllegalArgumentException("id is null"));
        if (busRepository.delete(id)) {
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
        return busRepository.findById(id)
                .map(Vehicle::getModel)
                .or(()-> Optional.of("NONE")).get();
    }

    //map
    //orElse
    public String getManufacturerValueById(String id) {
        return busRepository.findById(id)
                .map(Vehicle::getManufacturer)
                .map(String::valueOf)
                .orElse("NONE");
    }

    //map
    //orElseGet
    public String getPriceValueById(String id) {
        return busRepository.findById(id)
                .map(Vehicle::getPrice)
                .map(String::valueOf)
                .orElseGet(()->String.valueOf(new BigDecimal(-1)));
    }
}









