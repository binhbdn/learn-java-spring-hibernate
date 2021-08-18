package com.example.hibernate2.dao;

import java.util.List;
import java.util.Optional;

public interface InterfaceDAO<T, ID> {
    List<T> findAll();
    T save(T object);
    T update(T object);
    Optional<T> findById(ID id);
    List<T> findByIds(List<ID> ids);
    void delete(T object);
    void deleteById(ID id);
}
