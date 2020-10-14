package com.java.model;

import java.util.Set;

public class Customer {
    private Set<Specialty> specialties;
    private Account account;

    public Customer(Set<Specialty> specialties, Account account) {
        this.specialties = specialties;
        this.account = account;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public Account getAccount() {
        return account;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return account.getName();
    }
}
