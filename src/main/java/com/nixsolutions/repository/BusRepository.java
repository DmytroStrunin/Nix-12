package com.nixsolutions.repository;

import com.nixsolutions.model.Bus;

import java.util.LinkedList;
import java.util.List;

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
    public boolean create(Bus bus) {
        return buses.add(bus);
    }

    @Override
    public boolean create(List<Bus> bus) {
        return buses.addAll(bus);
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
        to.setManufacturer(from.getManufacturer());
        to.setModel(from.getModel());
        to.setNumberOfPassengers(from.getNumberOfPassengers());
        to.setPrice(from.getPrice());
    }
}
