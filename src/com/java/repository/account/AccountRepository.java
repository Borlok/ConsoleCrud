package com.java.repository.account;

import com.java.model.Account;
import com.java.model.Customer;
import com.java.repository.GenericRepository;

import java.util.List;

public interface AccountRepository extends GenericRepository<Account,Long> {
    List<Account> getAll();
}
