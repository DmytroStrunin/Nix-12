package com.nixsolutions.service;

import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.model.Truck;
import com.nixsolutions.repository.TruckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TruckServiceTest {

    private TruckService target;
    private TruckRepository truckRepository;

    @BeforeEach
    void setUp() {
        truckRepository = mock(TruckRepository.class);
        target = new TruckService(truckRepository);
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

    @Test
    void save_mustBeCallRepositoryMethod() {
        target.save(any());
        verify(truckRepository).saveAll(any());
    }

    @Test
    void printAll_mustBeCallRepositoryMethod() {
        target.printAll();
        verify(truckRepository).getAll();
    }

    @Test
    void update_mustBeCallTruckMethod() {
        Truck truckMock = mock(Truck.class);
        target.update(truckMock);
        verify(truckMock).getId();
    }

    @Test
    void delete_mustBeCallRepositoryMethod() {
        target.delete(anyString());
        verify(truckRepository).delete(anyString());
    }

    @Test
    void getRandomManufacturer_mustBeCallManufacturerMethod() {
        try (MockedStatic<Manufacturer> mockedStatic = mockStatic(Manufacturer.class)) {
            when(Manufacturer.values()).thenCallRealMethod();
            target.getRandomManufacturer();
            mockedStatic.verify(Manufacturer::values);
        } catch (Throwable e) {
            fail();
        }
    }
}