package com.nixsolutions.service;

import com.nixsolutions.model.Bus;
import com.nixsolutions.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mockConstruction;


class BusServiceTest {

    private BusService target;
    private BusRepository busRepository;

    @BeforeEach
    void setUp() {
        busRepository = Mockito.mock(BusRepository.class);
        target = new BusService(busRepository);
    }

    @Test
    void create_withActualSize() {
        int actualSize = target.create(5).size();
        assertEquals(5, actualSize);
    }

    @Test
    void save_mockito_verifyTimes() {
        List<Bus> buses = new LinkedList<>();
        target.save(buses);
        Mockito.verify(busRepository, Mockito.times(1)).saveAll(Mockito.any());
    }

    @Test
    void save_mockito_throwingException() {
        Mockito.when(busRepository.saveAll(Mockito.any())).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(Mockito.any()));
    }

    @Test
    void save_throwingException() {
        Mockito.when(busRepository.saveAll(null)).thenCallRealMethod();
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void printAll_mockito_verify() {
        target.printAll();
        Mockito.verify(busRepository).getAll();
    }

    @Test
    void update_mockito_callMockMethod() {
        Mockito.when(busRepository.update(Mockito.any())).thenReturn(true);
        Bus busMock = Mockito.mock(Bus.class);
        assertTrue(target.update(busMock));
    }

    @Test
    void delete_mockito_callRealMethod() {
        try (MockedConstruction<BusRepository> busRepositoryMockedConstruction =
                     mockConstruction(BusRepository.class,
                             (mock, context) ->
                                     doCallRealMethod()
                                             .when(mock).delete(anyString()))) {
            assertFalse(target.delete(Mockito.any()));
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    void getRandomManufacturer() {
    }
}