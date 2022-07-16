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
        Optional.ofNullable(id).orElseThrow(()-> new IllegalArgumentException("id is null"));
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
        Optional.ofNullable(bus).orElseThrow(()-> new IllegalArgumentException("bus is null"));
        Optional<Bus> bus1 = Optional.ofNullable(bus).orElseGet(());
        if (bus1.isPresent()){
            bus1.filter(vehicle-> findById(vehicle.getId()).isEmpty()).ifPresent(buses::add);
        }

        return buses.add(bus);
    }

    public boolean save1(Bus bus) {
        Optional.ofNullable(bus).orElseThrow(()-> new IllegalArgumentException("bus is null"));
        if (findById(bus.getId()).isPresent()){
            return false;
        }
        return buses.add(bus);
    }

    @Override
    public boolean saveAll(List<Bus> buses) {
        Optional.ofNullable(buses).orElseThrow(()-> new IllegalArgumentException("buses is null"));
        return this.buses.addAll(buses);
    }

    @Override
    public boolean update(Bus bus) {
        Optional.ofNullable(bus).orElseThrow(()-> new IllegalArgumentException("bus is null"));
        final Optional<Bus> founded = findById(bus.getId());
        if (founded.isPresent()){
            copy(bus, founded.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id).isPresent()) {
            return buses.remove(findById(id).get());
        }
        return false;
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
