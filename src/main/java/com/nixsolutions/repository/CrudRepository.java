package com.nixsolutions.repository;

import com.nixsolutions.model.Auto;
import com.nixsolutions.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Vehicle> {

    Optional<T> findById(String id);

    List<T> findAll();

    List<T> getAll();

    boolean save(T t);

    boolean saveAll(List<T> t);

    boolean update(T t);

    T update(int position);

    boolean delete(String id);

    boolean delete(int position);

    void copy(final T from, final T to);
}
