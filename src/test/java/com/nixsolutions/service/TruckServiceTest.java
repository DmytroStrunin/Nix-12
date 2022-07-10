package com.nixsolutions.service;

import com.nixsolutions.repository.BusRepository;
import com.nixsolutions.repository.TruckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TruckServiceTest {

    private TruckService target;
    private TruckRepository truckRepository;

    @BeforeEach
    void setUp() {
        truckRepository = Mockito.mock(TruckRepository.class);
        target = new TruckService(truckRepository);
    }

    @Test
    void create() {
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