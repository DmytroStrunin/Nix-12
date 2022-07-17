package com.nixsolutions.service;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusServiceTest {

    private BusService target;
    private BusRepository busRepository;
    private Bus bus;

    @BeforeEach
    void setUp() {
        busRepository = mock(BusRepository.class);
        target = new BusService(busRepository);
        bus = new Bus("model", Manufacturer.KIA,new BigDecimal(321),0);
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

    @Test
    void save_mockitoThrowingException() {
        when(busRepository.saveAll(any()))
                .thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> target.save(any()));
    }

    @Test
    void save_throwingException() {
        when(busRepository.saveAll(null)).thenCallRealMethod();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> target.save(null));
    }

    @Test
    void save_mockitoVerifyTimes() {
        target.save(any());
        verify(busRepository, times(1)).saveAll(any());
    }

    @Test
    void printAll_mockitoVerify() {
        target.printAll();
        verify(busRepository).getAll();
    }

    @Test
    void update_mockitoCallMockMethod() {
        when(busRepository.update(any())).thenReturn(true);
        Bus busMock = mock(Bus.class);
        assertTrue(target.update(busMock));
    }

    @Test
    void update_mockitoArgumentCaptor() {
        ArgumentCaptor<Bus> captor = ArgumentCaptor.forClass(Bus.class);
        target.update(new Bus("model", null, null, 0));
        verify(busRepository).update(captor.capture());
        Assertions.assertEquals("model", captor.getValue().getModel());
    }

    @Test
    void delete_mockitoCallRealMethod() {
        when(busRepository.delete(anyString())).thenCallRealMethod();
        assertFalse(target.delete(anyString()));
    }

    @Test
    void delete_mockitoCustomArgumentMatcher() {
        when(busRepository.delete(argThat(a -> a.equals("id"))))
                .thenReturn(true);
        assertTrue(target.delete("id"));
    }

    @Test
    void getModelValueById_mustBeReturnModelIfIdExist() {
        when(busRepository.findById(anyString()))
                .thenReturn(Optional.of(bus));
        assertEquals("model", target.getModelValueById("id"));
    }

    @Test
    void getManufacturerValueById_mustBeReturnManufacturerIfIdExist() {
        when(busRepository.findById(anyString()))
                .thenReturn(Optional.of(bus));
        assertEquals("KIA", target.getManufacturerValueById("id"));
    }

    @Test
    void getPriceValueById_mustBeReturnPriceIfIdExist() {
        when(busRepository.findById(anyString()))
                .thenReturn(Optional.of(bus));
        assertEquals("321", target.getPriceValueById("id"));
    }
}