package com.java.view.account;

import com.java.controllers.account.AccountControllerImpl;
import com.java.controllers.customer.CustomerController;
import com.java.controllers.customer.CustomerControllerImpl;
import com.java.controllers.specialty.SpecialityController;
import com.java.controllers.specialty.SpecialityControllerImpl;
import com.java.model.Account;
import com.java.model.AccountStatus;
import com.java.model.Customer;
import com.java.model.Specialty;
import com.java.view.ViewImpl;

import java.util.Scanner;

public class AccountViewImpl implements AccountView{
    private AccountControllerImpl accountController;
    private CustomerController customerController;
    private Scanner sc;

    public AccountViewImpl() {
        accountController = AccountControllerImpl.getInstance();
        customerController = CustomerControllerImpl.getInstance();
    }

    @Override
    public void main() {
        sc = new Scanner(System.in);
        int choice = 0;
        System.out.println("\n--Аккаунты--");
        System.out.println("Выберите действие:");
        System.out.println("1: Добавить аккаунт");
        System.out.println("2: Посмотреть аккаунты");
        System.out.println("3: Редактировать аккаунт");
        System.out.println("4: Удалить аккаунт");
        System.out.println("5: Назад");
        try {
            choice = sc.nextInt();
            while (true) {
                switch (choice) {
                    case 1: create();
                        break;
                    case 2: get();
                        break;
                    case 3: edit();
                        break;
                    case 4: delete();
                        break;
                    case 5: back();
                        break;

                    default:
                        System.out.print("\nТакого действия нет");
                        main();
                }
            }
        } catch (Exception e) {
            System.out.println("Введены неверные символы");
            main();
        }
    }

    @Override
    public void create() {
        createAccount();
        main();
    }

    public void createAccount() {
        sc = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = sc.next();
        accountController.createAccount(new Account(name, AccountStatus.ACTIVE));
    }

    @Override
    public void get() {
        getAll();
        System.out.println("Для продолжения введите любой символ...");
        sc.next();
        main();
    }

    public void getAll() {
        System.out.println("--Список аккаунтов--");
        for (int i = 0; i < accountController.getAll().size(); i++) {
            System.out.println("id: " + (i+1) + " | " + accountController.getAll().get(i) + " |");
        }
    }

    @Override
    public void edit() {
        sc = new Scanner(System.in);
        System.out.println("--Выберите аккаунт для замены--");
        getAll();
        long choice = sc.nextLong();
        System.out.println("Введите новое имя: ");
        String name = sc.next();
        Customer customer = new Customer(customerController.getCustomerById(choice).getSpecialties(),
                customerController.getCustomerById(choice).getAccount());
        customer.getAccount().setName(name);
        accountController.editAccount(customer.getAccount(),choice);
        customerController.editCustomer(customer,choice);
        main();
    }

    @Override
    public void delete() {
        sc = new Scanner(System.in);
        System.out.println("Выберите аккаунт для удаления:");
        getAll();
        long choice = sc.nextLong();
        customerController.deleteCustomerById(choice);
        accountController.deleteAccountById(choice);
        main();    }

    public void back() {
        new ViewImpl().main();
    }
}
