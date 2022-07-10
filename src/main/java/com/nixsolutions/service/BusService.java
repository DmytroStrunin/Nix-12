package com.nixsolutions.service;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.repository.BusRepository;
import com.nixsolutions.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusService.class);
    private final CrudRepository<Bus> busRepository;
    private static final Random RANDOM = new Random();

    public BusService(BusRepository busRepository){
        this.busRepository=busRepository;
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
            LOGGER.debug("Created bus {}", bus.getId());
        }
        return result;
    }

    private int getRandomNumberOfPassengers() {
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

    public boolean update(Bus bus) {
        if (busRepository.getById(bus.getId()) != null) {
            LOGGER.debug("Update auto {}", bus.getId());
        }
        return busRepository.update(bus);
    }

    public boolean delete(String id) {
        if (busRepository.delete(id)) {
            LOGGER.debug("Remove bus {}", id);
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
