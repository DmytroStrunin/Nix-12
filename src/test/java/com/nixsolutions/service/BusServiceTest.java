package com.nixsolutions.service;

import com.nixsolutions.repository.BusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BusServiceTest {

    private BusService target;
    private BusRepository busRepository;

    @BeforeEach
    void setUp() {
        busRepository = Mockito.mock(BusRepository.class);
        target = new BusService(busRepository);
    }

    @Test
    void create() {
        int actualSize = target.create(5).size();
        assertEquals(5, actualSize);
    }

    @Test
    void save() {
    }

    @Test
    void printAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getRandomManufacturer() {
    }
}