package com.nixsolutions.repository;

import com.nixsolutions.model.Truck;

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
    private final List<Truck> trucks;

    public TruckRepository() {
        trucks = new LinkedList<>();
    }

    @Override
    public Truck getById(String id) {
        return trucks.stream()
                .filter(truck -> truck.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Truck> getAll() {
        return trucks;
    }

    @Override
    public boolean save(Truck truck) {
        if (trucks.contains(truck)) {
            return false;
        }
        return trucks.add(truck);
    }

    @Override
    public boolean saveAll(List<Truck> trucks) {
        Optional.ofNullable(trucks).orElseThrow(IllegalArgumentException::new);
        return this.trucks.addAll(trucks);
    }

    @Override
    public boolean update(Truck truck) {
        final Truck founded = getById(truck.getId());
        if (founded != null) {
            copy(truck, founded);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return trucks.remove(getById(id));
    }

    @Override
    public void copy(final Truck from, final Truck to) {
        if (from != null && to != null) {
            to.setManufacturer(from.getManufacturer());
            to.setModel(from.getModel());
            to.setTransportedWeight(from.getTransportedWeight());
            to.setPrice(from.getPrice());
        }
    }
}
