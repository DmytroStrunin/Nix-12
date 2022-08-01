package com.nixsolutions.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.StringJoiner;

@Getter
@Setter
public class Auto extends Vehicle {
    private Body bodyType;
    private static int count;

    public Auto(String model, Manufacturer manufacturer, BigDecimal price, Body bodyType) {
        super(model, manufacturer, price);
        this.bodyType = bodyType;
        count++;
    }

    @Override
    public String toString() {
        return new StringJoiner("\t")
//                .add(id)
//                .add("price: " + price.toString())
                .add(model)
//                .add("name: " + manufacturer.toString())
//                .add(bodyType.toString())
//                .add("count: " + count)
//                .add(this.getClass().getSimpleName())
                .toString();
    }

    @Override
    public int getCount() {
        return count;
    }
}
