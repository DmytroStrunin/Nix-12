package com.nixsolutions.repository;


import com.nixsolutions.model.vehicle.Auto;
import com.nixsolutions.model.Body;
import com.nixsolutions.model.Manufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AutoRepositoryTest {

    private AutoRepository target;

    private Auto auto;

    @BeforeEach
    void setUp() {
        target = AutoRepository.getInstance();
        auto = createSimpleAuto();
        target.save(auto);
    }

    private Auto createSimpleAuto() {
        return new Auto("Model", Manufacturer.BMW, BigDecimal.ZERO, Body.COUPE);
    }

    @Test
    void getById_findOne() {
        final Optional<Auto> actual = target.findById(auto.getId());
        Assertions.assertNotNull(actual.get());
        Assertions.assertEquals(auto.getId(), actual.get().getId());
    }

    @Test
    void getById_notFind() {
        final Optional<Auto> actual = target.findById("1232");
        Assertions.assertNull(actual.orElse(null));
    }

    @Test
    void getById_findOne_manyAutos() {
        final Auto otherAuto = createSimpleAuto();
        target.save(otherAuto);
        final Optional<Auto> actual = target.findById(auto.getId());
        Assertions.assertNotNull(actual.get());
        Assertions.assertEquals(auto.getId(), actual.get().getId());
        Assertions.assertNotEquals(otherAuto.getId(), actual.get().getId());
    }

    @Order(1)
    @Test
    void getAll() {
        final List<Auto> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void save_success_notChangePrice() {
        auto.setPrice(BigDecimal.ONE);
        final boolean actual = target.save(auto);
        Assertions.assertTrue(actual);
        final Optional<Auto> actualAuto = target.findById(auto.getId());
        Assertions.assertEquals(BigDecimal.ONE, actualAuto.get().getPrice());
    }

    @Test
    void save_fail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.save(null));
    }

    @Test
    void save_success_changePrice() {
        target.save(auto);
        final Optional<Auto> actual = target.findById(auto.getId());
        Assertions.assertEquals(BigDecimal.valueOf(-1), actual.get().getPrice());
    }

    @Test
    void saveAll_null() {
        final boolean actual = target.saveAll(null);
        Assertions.assertFalse(actual);
    }

    @Test
    void saveAll_emptyList() {
        final boolean actual = target.saveAll(Collections.emptyList());
        Assertions.assertFalse(actual);
    }

    @Test
    void saveAll() {
        final boolean actual = target.saveAll(List.of(createSimpleAuto()));
        Assertions.assertTrue(actual);
    }

    @Test
    void update_notFound() {
        final Auto otherAuto = createSimpleAuto();
        final boolean actual = target.update(otherAuto);
        Assertions.assertFalse(actual);
    }

    @Test
    void update() {
        auto.setPrice(BigDecimal.TEN);
        final boolean actual = target.update(auto);
        Assertions.assertTrue(actual);
        final Optional<Auto> actualAuto = target.findById(auto.getId());
        Assertions.assertEquals(BigDecimal.TEN, actualAuto.get().getPrice());
    }

    @Test
    void updateByBodyType() {
        final Auto otherAuto = createSimpleAuto();
        otherAuto.setManufacturer(Manufacturer.KIA);
        otherAuto.setPrice(BigDecimal.TEN);

        final boolean actual = target.updateByBodyType(auto.getBodyType(), otherAuto);
        Assertions.assertTrue(actual);
        final Optional<Auto> actualAuto = target.findById(auto.getId());
        Assertions.assertEquals(Manufacturer.KIA, actualAuto.get().getManufacturer());
        Assertions.assertEquals(BigDecimal.TEN, actualAuto.get().getPrice());
    }

    @Test
    void delete() {
    }
}