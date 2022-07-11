package com.nixsolutions.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.StringJoiner;

@Getter
@Setter
public class Auto extends Vehicle {
    private Body bodyType;

    public Auto(String model, Manufacturer manufacturer, BigDecimal price, Body bodyType) {
        super(model, manufacturer, price);
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return new StringJoiner("\t")
                .add(id)
                .add(price.toString())
                .add(model)
                .add(manufacturer.toString())
                .add(bodyType.toString())
                .toString();
    }
}
