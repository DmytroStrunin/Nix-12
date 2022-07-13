package com.nixsolutions.repository;

import com.nixsolutions.model.Bus;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code BusRepository} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class BusRepository implements CrudRepository<Bus> {
    private final List<Bus> buses;

    public BusRepository() {
        buses = new LinkedList<>();
    }

    @Override
    public Bus getById(String id) {
        return buses.stream()
                .filter(bus -> bus.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Bus> getAll() {
        return buses;
    }

    @Override
    public boolean save(Bus bus) {
        if (buses.contains(bus)) {
            return false;
        }
        return buses.add(bus);
    }

    @Override
    public boolean saveAll(List<Bus> buses) {
        Optional.ofNullable(buses).orElseThrow(IllegalArgumentException::new);
        return this.buses.addAll(buses);
    }

    @Override
    public boolean update(Bus bus) {
        final Bus founded = getById(bus.getId());
        if (founded != null) {
            copy(bus, founded);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return buses.remove(getById(id));
    }

    @Override
    public void copy(final Bus from, final Bus to) {
        if (from != null && to != null) {
            to.setManufacturer(from.getManufacturer());
            to.setModel(from.getModel());
            to.setNumberOfPassengers(from.getNumberOfPassengers());
            to.setPrice(from.getPrice());
        }
    }
}
