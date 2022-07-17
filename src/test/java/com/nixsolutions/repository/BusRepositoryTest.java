package com.nixsolutions.repository;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BusRepositoryTest {

    private BusRepository target;
    private Bus bus;

    @BeforeEach
    void setUp() {
        target = new BusRepository();
        bus = new Bus("model", Manufacturer.KIA, BigDecimal.ZERO, 0);
        target.save(bus);
    }

    @Test
    void getById_mustBeReturnBusWithId() {
        assertEquals(bus, target.getById(bus.getId()));
    }

    @Test
    void getAll_MustBeReturnOneBus() {
        assertEquals(1, target.getAll().size());
    }

    @Test
    void save_MustBeReturnFalseIfRepositoryContains() {
        assertFalse(target.save(bus));
    }

    @Test
    void saveAll_MustBeTrowExceptionIfArgumentNull() {
        assertThrows(IllegalArgumentException.class, () -> target.saveAll(null));
    }

    @Test
    void update_MustBeReturnTrueIfUpdatePassed() {
        assertTrue(target.update(bus));
    }

    @Test
    void delete_MustBeReturnTrueIfBusRemove() {
        assertTrue(target.delete(bus.getId()));
    }

    @Test
    void copy_MustBeCopyObjectFields() {
        Bus copiedBus = new Bus(null, null, null, 0);
        target.copy(bus, copiedBus);
        assertEquals(copiedBus, bus);
    }
}