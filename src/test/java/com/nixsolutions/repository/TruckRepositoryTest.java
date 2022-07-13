package com.nixsolutions.repository;

import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TruckRepositoryTest {

    private TruckRepository target;
    private Truck truck;
    private Truck nullTruck;

    @BeforeEach
    void setUp() {
        target = new TruckRepository();
        truck = new Truck("model", Manufacturer.KIA, BigDecimal.ZERO, 0);
        target.save(truck);
        nullTruck = new Truck(null, null, null, 0);
    }

    @Test
    void getById_mustBeReturnNullWhenRepositoryDontContains() {
        assertNull(target.getById("none id"));
    }

    @Test
    void getAll_mustBeContainsTruck() {
        assertTrue(target.getAll().contains(truck));
    }

    @Test
    void save_mustBeReturnTrueIfRepositoryDontContains() {
        assertTrue(target.save(nullTruck));
    }

    @Test
    void saveAll_mustBeReturnTrueIfTrucksSaved() {
        assertTrue(target.saveAll(List.of(nullTruck)));
    }

    @Test
    void update_mustBeReturnFalseIfUpdateDontPassed() {
        assertFalse(target.update(nullTruck));
    }

    @Test
    void delete_mustBeReturnFalseIfBusDontRemove() {
        assertFalse(target.delete(nullTruck.getId()));
    }

    @Test
    void copy_dontChangeTruck() {
        target.copy(truck, null);
        assertNotNull(truck);
    }
}