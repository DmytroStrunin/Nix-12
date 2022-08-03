package com.nixsolutions.model.vehicle;

import com.nixsolutions.model.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class Vehicle {
    protected final String id;
    protected String model;
    protected Manufacturer manufacturer;
    protected BigDecimal price;
    protected String currency;
    protected LocalDateTime created;
    protected Engine engine;
    protected List<String> details;

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

    @Getter
    @Setter
    public static class Engine{
        int volume;
        String brandt;
    }
}
