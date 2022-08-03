package com.nixsolutions.service;

import com.nixsolutions.model.vehicle.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VehicleServiceTest {

    private VehicleService<Bus> target;
    private BusRepository repository;
    private Bus bus;

    @BeforeEach
    void setUp() {
        repository = mock(BusRepository.class);
        target = new BusService(repository);
        bus = new Bus("model", Manufacturer.KIA, new BigDecimal(321), 0);
    }

    @Test
    void save_mockitoThrowingException() {
        when(repository.saveAll(any()))
                .thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> target.save(any()));
    }

    @Test
    void save_throwingException() {
        when(repository.saveAll(null)).thenCallRealMethod();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> target.save(null));
    }

    @Test
    void save_mockitoVerifyTimes() {
        target.save(any());
        verify(repository, times(1)).saveAll(any());
    }

    @Test
    void save_mustBeCallRepositoryMethod() {
        target.save(any());
        verify(repository).saveAll(any());
    }

    @Test
    void printAll_mustBeCallRepositoryMethod() {
        target.printAll();
        verify(repository).getAll();
    }

    @Test
    void printAll_mockitoVerify() {
        target.printAll();
        verify(repository).getAll();
    }

    @Test
    void update_mockitoCallMockMethod() {
        when(repository.update(any())).thenReturn(true);
        Bus busMock = mock(Bus.class);
        assertTrue(target.update(busMock));
    }

    @Test
    void update_mockitoArgumentCaptor() {
        ArgumentCaptor<Bus> captor = ArgumentCaptor.forClass(Bus.class);
        target.update(new Bus("model", null, null, 0));
        verify(repository).update(captor.capture());
        Assertions.assertEquals("model", captor.getValue().getModel());
    }

    @Test
    void update_mustBeCallTruckMethod() {
        Bus mock = mock(Bus.class);
        target.update(mock);
        verify(mock).getId();
    }

    @Test
    void delete_mustBeCallRepositoryMethod() {
        target.delete(anyString());
        verify(repository).delete(anyString());
    }

    @Test
    void delete_mockitoCallRealMethod() {
        when(repository.delete(anyString())).thenCallRealMethod();
        assertFalse(target.delete(anyString()));
    }

    @Test
    void delete_mockitoCustomArgumentMatcher() {
        when(repository.delete(argThat(a -> a.equals("id"))))
                .thenReturn(true);
        assertTrue(target.delete("id"));
    }

    @Test
    void getModelValueById_mustBeReturnModelIfIdExist() {
        when(repository.findById(anyString()))
                .thenReturn(Optional.of(bus));
        assertEquals("model", target.getModelValueById("id"));
    }

    @Test
    void getModelValueById_mustBeReturnDefaultValueIfIdDontExist() {
        when(repository.findById(anyString()))
                .thenReturn(Optional.empty());
        assertEquals("NONE", target.getModelValueById("id"));
    }

    @Test
    void getManufacturerValueById_mustBeReturnManufacturerIfIdExist() {
        when(repository.findById(anyString()))
                .thenReturn(Optional.of(bus));
        assertEquals("KIA", target.getManufacturerValueById("id"));
    }

    @Test
    void getManufacturerValueById_mustBeReturnDefaultValueIfIdDontExist() {
        when(repository.findById(anyString()))
                .thenReturn(Optional.empty());
        assertEquals("NONE", target.getManufacturerValueById("id"));
    }

    @Test
    void getPriceValueById_mustBeReturnPriceIfIdExist() {
        when(repository.findById(anyString()))
                .thenReturn(Optional.of(bus));
        assertEquals("321", target.getPriceValueById("id"));
    }

    @Test
    void getPriceValueById_mustBeReturnDefaultValueIfIdDontExist() {
        when(repository.findById(anyString()))
                .thenReturn(Optional.empty());
        assertEquals("-1", target.getPriceValueById("id"));
    }
}