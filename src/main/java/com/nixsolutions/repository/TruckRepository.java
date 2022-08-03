package com.nixsolutions.repository;

import com.nixsolutions.model.vehicle.Truck;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code TruckRepository} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class TruckRepository implements CrudRepository<Truck> {
    private static TruckRepository instance;
    private final List<Truck> trucks;

    private TruckRepository() {
        trucks = new LinkedList<>();
    }

    public static TruckRepository getInstance(){
        if (instance == null) {
            instance=new TruckRepository();
        }
        return instance;
    }

    //filter
    @Override
    public Optional<Truck> findById(String id) {
        Optional.ofNullable(id).orElseThrow(()-> new IllegalArgumentException("id is null"));
        return trucks.stream()
                .filter(bus -> bus.getId().equals(id))
                .findFirst();
    }

    @Override
    public Truck update(int position) {
        return trucks.get(position);
    }

    @Override
    public List<Truck> findAll() {
        return trucks;
    }

    @Override
    public List<Truck> getAll() {
        return trucks;
    }

    //ifPresent
    //orElseThrow
    @Override
    public boolean save(Truck truck) {
        Optional<Truck> optionalBus = Optional.ofNullable(truck);
        if (findById(optionalBus.orElseThrow().getId()).isEmpty()){
            optionalBus.filter(vehicle-> findById(vehicle.getId()).isEmpty())
                    .ifPresent(trucks::add);
            return true;
        }
        return false;
    }

    //orElseThrow
    @Override
    public boolean saveAll(List<Truck> trucks) {
        Optional.ofNullable(trucks).orElseThrow(()-> new IllegalArgumentException("buses is null"));
        return this.trucks.addAll(trucks);
    }

    //orElseThrow
    @Override
    public boolean update(Truck truck) {
        Optional.ofNullable(truck).orElseThrow(()-> new IllegalArgumentException("bus is null"));
        final Optional<Truck> founded = findById(truck.getId());
        if (founded.isPresent()){
            copy(truck, founded.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (findById(id).isPresent()) {
            return trucks.remove(findById(id).get());
        }
        return false;
    }

    @Override
    public boolean delete(int position) {
        trucks.remove(position);
        return true;
    }

    //ifPresentOrElse
    @Override
    public void copy(final Truck from, final Truck to) {
        Truck truckTo = Optional.ofNullable(to).orElseThrow(()-> new IllegalArgumentException("argument truck to is null"));
        Optional.ofNullable(from).ifPresentOrElse(truckFrom -> {
            truckTo.setManufacturer(truckFrom.getManufacturer());
            truckTo.setModel(truckFrom.getModel());
            truckTo.setTransportedWeight(truckFrom.getTransportedWeight());
            truckTo.setPrice(truckFrom.getPrice());
        },()-> {throw new IllegalArgumentException("argument truck from is null");});
    }
}
