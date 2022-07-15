package com.nixsolutions.repository;

import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Vehicle> {

    Optional<T> findById(String id);

    List<T> getAll();

    boolean save(T t);

    boolean saveAll(List<T> t);

    boolean update(T t);

    boolean delete(String id);

    void copy(final T from, final T to);
}
