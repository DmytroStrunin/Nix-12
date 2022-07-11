package com.nixsolutions.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * The {@code Truck} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
@Getter
@Setter
public class Truck extends Vehicle {
    private int transportedWeight;

    public Truck(String model, Manufacturer manufacturer, BigDecimal price, int transportedWeight) {
        super(model, manufacturer, price);
        this.transportedWeight = transportedWeight;
    }

    @Override
    public String toString() {
        return new StringJoiner("\t")
                .add(id)
                .add(price.toString())
                .add(model)
                .add(manufacturer.toString())
                .add(String.valueOf(transportedWeight))
                .toString();
    }
}
