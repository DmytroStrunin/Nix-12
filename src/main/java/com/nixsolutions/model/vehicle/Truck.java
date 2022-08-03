package com.nixsolutions.model.vehicle;

import com.nixsolutions.model.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
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
    private static int count;

    public Truck(String model, Manufacturer manufacturer, BigDecimal price, int transportedWeight) {
        super(model, manufacturer, price);
        this.transportedWeight = transportedWeight;
        count++;
    }

    @Override
    public String toString() {
        return new StringJoiner("\t")
//                .add(id)
//                .add("price: " + price.toString())
                .add(model)
//                .add("name: " + manufacturer.toString())
//                .add(String.valueOf(transportedWeight))
//                .add("count: " + count)
//                .add(this.getClass().getSimpleName())
                .toString();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck truck)) return false;
        if (!super.equals(o)) return false;
        return getTransportedWeight() == truck.getTransportedWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTransportedWeight());
    }
}
