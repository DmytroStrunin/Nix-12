package com.nixsolutions.service;

import com.nixsolutions.repository.TruckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TruckServiceTest {

    private TruckService target;

    @BeforeEach
    void setUp() {
        target = new TruckService(new TruckRepository());
    }

    @Test
    void create_mustBeReturnEmptyListWhenNegativeArgument() {
        assertEquals(List.of(), target.create(-1));
    }

    @Test
    void getRandomTransportedWeight_shouldBeReturnNormalValue() {
        int randomNumber = target.getRandomTransportedWeight();
        assertTrue(randomNumber >= 100 && randomNumber <= 200);
    }
}