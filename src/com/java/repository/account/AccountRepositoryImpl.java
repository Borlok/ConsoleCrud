package com.java.repository.account;

import com.java.model.Account;
import com.java.model.AccountStatus;
import com.java.repository.customer.CustomerRepositoryImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountRepositoryImpl implements AccountRepository{
    private static AccountRepositoryImpl accountRepository;
    private static CustomerRepositoryImpl customerRepository = CustomerRepositoryImpl.getInstance();
    private List<Account> accounts;
    private File file = new File("accounts.txt");

    @Override
    public void create(Account account) {
        accounts = getAll();
        try (PrintWriter writer = new PrintWriter("accounts.txt")) {
            accounts.add(account);
//            System.out.println(Arrays.toString(accounts.toArray()));//
            for (int i = 0; i < accounts.size(); i++) {
                writer.println((i + 1) + "|" + accounts.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try (PrintWriter writer = new PrintWriter("accounts.txt")) {
            for (int i = 0; i < accounts.size(); i++) {
                writer.println((i + 1) + "|" + accounts.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getById(Long id) {
        return getAll().get((int) (id - 1));
    }

    @Override
    public void update(Account account, Long id) {
        accounts = getAll();
        accounts.set((int) (id - 1),account);
        create();
    }

    @Override
    public void delete(Long id) {
        accounts = getAll();
        accounts.set((int) (id - 1),null);
        create();
    }

    @Override
    public List<Account> getAll() {
        accounts = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                accounts.add(new Account(sc.skip("\\d\\|").nextLine(), AccountStatus.ACTIVE));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    private AccountRepositoryImpl() {

    }

    public static AccountRepositoryImpl getInstance() {
        if (accountRepository == null)
            accountRepository = new AccountRepositoryImpl();
        return accountRepository;
    }
}
