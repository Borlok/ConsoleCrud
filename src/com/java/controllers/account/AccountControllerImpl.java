package com.java.controllers.account;

import com.java.model.Account;
import com.java.repository.account.AccountRepositoryImpl;

import java.util.List;

public class AccountControllerImpl implements AccountController{
    private static AccountControllerImpl controller;
    private static AccountRepositoryImpl repository;

    @Override
    public void createAccount(Account account) {
        repository.create(account);
    }

    @Override
    public Account getAccountById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Account> getAll() {
        return repository.getAll();
    }

    @Override
    public void editAccount(Account account, long id) {
        repository.update(account,id);
    }

    @Override
    public void deleteAccountById(long id) {
        repository.delete(id);
    }

    private AccountControllerImpl() {
        repository = AccountRepositoryImpl.getInstance();
    }

    public static AccountControllerImpl getInstance() {
        if (controller == null)
            controller = new AccountControllerImpl();
        return controller;
    }
}
