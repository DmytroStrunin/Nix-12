package com.nixsolutions.utils;

import com.nixsolutions.model.Vehicle;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

/**
 * The {@code VehicleContainer} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class VehicleContainer<T extends Vehicle> {

    private final T t;

    public VehicleContainer(T t) {
        Optional.ofNullable(t)
                .map(Vehicle::getPrice)
                .orElseThrow(IllegalArgumentException::new);
        this.t = t;
    }

    public T getVehicle() {
        return t;
    }

    public T setRandomDiscount(){
        BigDecimal discount = t.getPrice().multiply(new BigDecimal(new Random().nextInt(10,30)).divide(new BigDecimal(100)));
        t.setPrice(t.getPrice().add(discount));
        return t;
    }

    public <N extends Number> T upPrice(N n){
        t.setPrice(t.getPrice().add(new BigDecimal(n.toString())));
        return t;
    }
}
