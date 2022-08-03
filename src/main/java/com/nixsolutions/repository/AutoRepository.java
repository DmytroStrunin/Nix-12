package com.nixsolutions.repository;

import com.nixsolutions.model.vehicle.Auto;
import com.nixsolutions.model.Body;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AutoRepository implements CrudRepository<Auto> {
    private static AutoRepository instance;
    private final List<Auto> autos;

    private AutoRepository() {
        autos = new LinkedList<>();
    }

    public static AutoRepository getInstance() {
        if (instance == null) {
            instance=new AutoRepository();
        }
        return instance;
    }

    @Override
    public Optional<Auto> findById(String id) {
        for (Auto auto : autos) {
            if (auto.getId().equals(id)) {
                return Optional.of(auto);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Auto> findAll() {
        return autos;
    }

    @Override
    public List<Auto> getAll() {
        return autos;
    }

    @Override
    public boolean save(Auto auto) {
        if (auto == null) {
            throw new IllegalArgumentException("Auto must not be null");
        }
        if (auto.getPrice().equals(BigDecimal.ZERO)) {
            auto.setPrice(BigDecimal.valueOf(-1));
        }
        autos.add(auto);
        return true;
    }

    @Override
    public boolean saveAll(List<Auto> auto) {
        if (auto == null) {
            return false;
        }
        return autos.addAll(auto);
    }

    @Override
    public boolean update(Auto auto) {
        final Optional<Auto> optionalAuto = findById(auto.getId());
        if (optionalAuto.isPresent()) {
            optionalAuto.ifPresent(founded -> copy(auto, founded));
            return true;
        }
        return false;
    }

    @Override
    public Auto update(int position) {
        return autos.get(position);
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Auto> iterator = autos.iterator();
        while (iterator.hasNext()) {
            final Auto auto = iterator.next();
            if (auto.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateByBodyType(Body bodyType, Auto copyFrom) {
        for (Auto auto : autos) {
            if (auto.getBodyType().equals(bodyType)) {
                copy(copyFrom, auto);
            }
        }
        return true;
    }

    @Override
    public boolean delete(int position) {
        autos.remove(position);
        return true;
    }


    @Override
    public void copy(final Auto from, final Auto to) {
        to.setManufacturer(from.getManufacturer());
        to.setModel(from.getModel());
        to.setBodyType(from.getBodyType());
        to.setPrice(from.getPrice());

    }
}
