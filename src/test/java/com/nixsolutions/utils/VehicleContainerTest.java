package com.nixsolutions.utils;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VehicleContainerTest {

    private Vehicle vehicle;
    private VehicleContainer<Vehicle> target;

    @BeforeEach
    void setUp() {
        vehicle = new Bus("model", Manufacturer.KIA, new BigDecimal(100), 10);
        target = new VehicleContainer<>(vehicle);
    }

    @Test
    void constructor_mustBeThrowExceptionIfVehicleEqualsNull() {
        assertThrows(IllegalArgumentException.class, () -> target = new VehicleContainer<>(null));
    }

    @Test
    void constructor_mustBeThrowExceptionIfVehiclePriceEqualsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> target = new VehicleContainer<>(new Bus("model", Manufacturer.KIA, null, 10)));
    }

    @Test
    void getVehicle_mustBeReturnTheSameVehicle() {
        assertEquals(vehicle, target.getVehicle());
    }

    @Test
    void setRandomDiscount_mustBeReturnRightDiscount() {
        int price = target.setRandomDiscount().getPrice().intValue();
        assertTrue(price >= 110 && price <= 130);
    }

    @Test
    void upPrice_mustBeReturnPricePlusMethodArgument() {
        int price = target.upPrice(10).getPrice().intValue();
        assertEquals(110, price);
    }
}