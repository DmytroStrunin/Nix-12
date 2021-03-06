package com.nixsolutions.repository;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BusRepositoryTest {

    private BusRepository target;
    private Bus bus;

    @BeforeEach
    void setUp() {
        target = BusRepository.getInstance();
        bus = new Bus("model", Manufacturer.KIA, BigDecimal.ZERO, 0);
        target.save(bus);
    }

    @Test
    void findById_mustBeReturnBusWithId() {
        assertEquals(Optional.of(bus), target.findById(bus.getId()));
    }

    @Order(1)
    @Test
    void getAll_MustBeReturnOneBus() {
        assertEquals(1, target.getAll().size());
    }

    @Test
    void save_MustBeReturnFalseIfRepositoryContains() {
        assertFalse(target.save(bus));
    }

    @Test
    void save_MustBeReturnTrueIfRepositoryDontContains() {
        assertTrue(target.save(new Bus(null, null, null, 0)));
    }

    @Test
    void save_MustBeThrowExceptionIfArgumentNull() {
        assertThrows(NoSuchElementException.class, () -> target.save(null));
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