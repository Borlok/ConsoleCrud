package com.java.repository.customer;

import com.java.model.Customer;
import com.java.model.Specialty;
import com.java.repository.GenericRepository;

import java.util.List;

public interface CustomerRepository extends GenericRepository<Customer,Long> {
    List<Customer> getAll();
}
