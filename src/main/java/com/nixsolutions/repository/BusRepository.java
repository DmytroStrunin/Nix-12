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
    public Optional<Bus> findById(String id) {
        return buses.stream()
                .filter(bus -> bus.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Bus> getAll() {
        return buses;
    }

    @Override
    public boolean save(Bus bus) {
        Optional<Bus> bus1 = Optional.ofNullable(bus).orElseGet(());
        if (bus1.isPresent()){
            bus1.filter(vehicle-> findById(vehicle.getId()).isEmpty()).ifPresent(buses::add);
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
        final Bus founded = findById(bus.getId()).orElseThrow();
        copy(bus, founded);
        return true;
    }

    @Override
    public boolean delete(String id) {
        return buses.remove(findById(id));
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
