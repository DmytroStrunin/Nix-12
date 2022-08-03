package com.nixsolutions.service;

import com.nixsolutions.model.vehicle.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import com.nixsolutions.repository.AutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

class AutoServiceTest {

    private AutoService target;
    private AutoRepository autoRepository;

    @BeforeEach
    void setUp() {
        autoRepository = Mockito.mock(AutoRepository.class);
        target = new AutoService(autoRepository);
    }

    @Test
    void createAutos_negativeCount() {
        final List<Auto> actual = target.createAndSaveVehicles(-1);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createAutos_zeroCount() {
        final List<Auto> actual = target.createAndSaveVehicles(0);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void createAutos() {
        final List<Auto> actual = target.createAndSaveVehicles(5);
        Assertions.assertEquals(5, actual.size());
        Mockito.verify(autoRepository, Mockito.times(5))
                .save(Mockito.any());
    }

    @Test
    void saveAutos() {
    }

    @Test
    void printAll() {
        List<Auto> autos = List.of(createSimpleAuto().get(), createSimpleAuto().get());
        Mockito.when(autoRepository.getAll()).thenReturn(autos);
        target.printAll();
    }

    private Optional<Auto> createSimpleAuto() {

        return Optional.of(new Auto("Model", Manufacturer.BMW, BigDecimal.ZERO, Body.COUPE));
    }

    @Test
    void findOneById_null1() {
        final Optional<Auto> expected = createSimpleAuto();
        Mockito.when(autoRepository.findById("")).thenReturn(expected);
        final Optional<Auto> actual = target.findOneById(null);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
    }

    @Test
    void findOneById_null2() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findOneById(null);
        Mockito.verify(autoRepository).findById(captor.capture());
        Assertions.assertEquals("", captor.getValue());
    }


}