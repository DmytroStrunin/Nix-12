package com.nixsolutions.service;

import com.nixsolutions.model.Bus;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BusServiceTest {

    private BusService target;
    private BusRepository busRepository;

    @BeforeEach
    void setUp() {
        busRepository = mock(BusRepository.class);
        target = new BusService(busRepository);
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
        List<Bus> buses = new LinkedList<>();
        target.save(buses);
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
        try (MockedConstruction<BusRepository> busRepositoryMockedConstruction =
                     mockConstruction(BusRepository.class,
                             (mock, context) ->
                                     doCallRealMethod()
                                             .when(mock).delete(anyString()))) {
            assertFalse(target.delete(any()));
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void delete_mockitoCustomArgumentMatcher() {
        when(busRepository.delete(argThat(a -> a.equals("id"))))
                .thenReturn(true);
        assertTrue(target.delete("id"));
    }

    @Test
    void getRandomManufacturer_mustBeReturnManufacturer() {
        target.getRandomManufacturer();
        assertEquals(Manufacturer.class, target.getRandomManufacturer().getClass());
    }
}