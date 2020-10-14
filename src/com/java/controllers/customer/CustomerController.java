package com.java.controllers.customer;

import com.java.model.Account;
import com.java.model.Customer;
import com.java.model.Specialty;

import java.util.List;
import java.util.Set;

public interface CustomerController {
    void createCustomer(List<Specialty> specialties, Account account);
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    List<Customer> getAll();
    void editCustomer(Customer customer, long id);
    void deleteCustomerById(long id);
}
