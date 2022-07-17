package com.nixsolutions.service;

import com.nixsolutions.repository.BusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusServiceTest {

    private BusService target;

    @BeforeEach
    void setUp() {
        target = new BusService(new BusRepository());
    }

    @Test
    void create_withActualSize() {
        int actualSize = target.create(5).size();
        assertEquals(5, actualSize);
    }

    @Test
    void getRandomNumberOfPassengers_shouldBeReturnNormalValue() {
        int randomNumber = target.getRandomNumberOfPassengers();
        assertTrue(randomNumber >= 20 && randomNumber <= 30);
    }
}