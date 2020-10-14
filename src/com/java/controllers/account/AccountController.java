package com.java.controllers.account;

import com.java.model.Account;
import com.java.model.Specialty;

import java.util.List;

public interface AccountController {
    void createAccount (Account account);
    Account getAccountById(long id);
    List<Account> getAll();
    void editAccount(Account account, long id);
    void deleteAccountById(long id);
}
