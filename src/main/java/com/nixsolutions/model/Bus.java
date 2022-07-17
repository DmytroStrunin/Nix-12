package com.nixsolutions.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code Bus} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
@Getter
@Setter
public class Bus extends Vehicle {
    private int numberOfPassengers;

    public Bus(String model, Manufacturer manufacturer, BigDecimal price, int numberOfPassengers) {
        super(model, manufacturer, price);
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public String toString() {
        return new StringJoiner("\t")
                .add(id)
                .add(price.toString())
                .add(model)
                .add(manufacturer.toString())
                .add(String.valueOf(numberOfPassengers))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus bus)) return false;
        if (!super.equals(o)) return false;
        return getNumberOfPassengers() == bus.getNumberOfPassengers();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumberOfPassengers());
    }
}
