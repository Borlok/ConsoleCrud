package com.java.repository;

import com.java.model.Customer;

public interface GenericRepository<T,ID> {
    void create(T t);
    T getById(ID id);
    void update(T t, ID id);
    void delete(ID id);
}
