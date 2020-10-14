package com.java.controllers.customer;


import com.java.model.Account;
import com.java.model.Customer;
import com.java.model.Specialty;
import com.java.repository.customer.CustomerRepository;
import com.java.repository.customer.CustomerRepositoryImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerControllerImpl implements CustomerController {
    private static CustomerControllerImpl controller;
    private static CustomerRepository repository;
    private long ID_COUNT;

    private CustomerControllerImpl() {
        repository = CustomerRepositoryImpl.getInstance();
    }

    @Override
    public void createCustomer(List<Specialty> specialties, Account account) {
//        System.out.println("КастКонтр");//
        Set<Specialty> special = new HashSet<>(specialties);
//        System.out.println("КастКонтр");//
        Customer customer = new Customer(special, account);
//        System.out.println("КастКонтр");//
        saveCustomer(customer);
    }

    @Override
    public void saveCustomer(Customer customer) {
        repository.create(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Customer> getAll() {
        return repository.getAll();
    }

    @Override
    public void editCustomer(Customer customer, long id) {
        repository.update(customer,id);
    }

    @Override
    public void deleteCustomerById(long id) {
        repository.delete(id);
    }

    public static CustomerControllerImpl getInstance () {
        if (controller == null)
            controller = new CustomerControllerImpl();
        return controller;
    }
}
