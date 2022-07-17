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

    //filter
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

    //ifPresent
    //orElseThrow
    @Override
    public boolean save(Bus bus) {
        Optional<Bus> optionalBus = Optional.ofNullable(bus);
        if (findById(optionalBus.orElseThrow().getId()).isEmpty()){
            optionalBus.filter(vehicle-> findById(vehicle.getId()).isEmpty())
                    .ifPresent(buses::add);
            return true;
        }
        return false;
    }

    //orElseThrow
    @Override
    public boolean saveAll(List<Bus> buses) {
        Optional.ofNullable(buses).orElseThrow(()-> new IllegalArgumentException("buses is null"));
        return this.buses.addAll(buses);
    }

    //orElseThrow
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

    //ifPresentOrElse
    @Override
    public void copy(final Bus from, final Bus to) {
        Bus busTo = Optional.ofNullable(to).orElseThrow(()-> new IllegalArgumentException("argument bus to is null"));
        Optional.ofNullable(from).ifPresentOrElse(busFrom -> {
            busTo.setManufacturer(busFrom.getManufacturer());
            busTo.setModel(busFrom.getModel());
            busTo.setNumberOfPassengers(busFrom.getNumberOfPassengers());
            busTo.setPrice(busFrom.getPrice());
        },()-> {throw new IllegalArgumentException("argument bus from is null");});
    }
}
