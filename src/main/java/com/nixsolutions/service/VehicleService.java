package com.nixsolutions.service;


import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Vehicle;

import java.util.List;
import java.util.Random;

public interface VehicleService<T extends Vehicle> {

    Random RANDOM = new Random();

    List<T> create(int count);

    void save(List<T> t);

    void printAll();

    boolean update(T t);

    boolean delete(String id);

    default Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
