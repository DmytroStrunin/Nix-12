package com.nixsolutions.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class Vehicle {
    protected final String id;
    protected String model;
    protected BigDecimal price;
    protected Manufacturer manufacturer;

    public abstract int getCount();

    protected Vehicle(String model, Manufacturer manufacturer, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(getModel(), vehicle.getModel()) && Objects.equals(getPrice(), vehicle.getPrice()) && getManufacturer() == vehicle.getManufacturer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getPrice(), getManufacturer());
    }
}
