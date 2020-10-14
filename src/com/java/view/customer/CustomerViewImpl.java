package com.java.view.customer;

import com.java.controllers.account.AccountController;
import com.java.controllers.account.AccountControllerImpl;
import com.java.controllers.customer.CustomerController;
import com.java.controllers.customer.CustomerControllerImpl;
import com.java.controllers.specialty.SpecialityController;
import com.java.controllers.specialty.SpecialityControllerImpl;
import com.java.model.Account;
import com.java.model.AccountStatus;
import com.java.model.Customer;
import com.java.model.Specialty;
import com.java.view.View;
import com.java.view.ViewImpl;
import com.java.view.account.AccountView;
import com.java.view.account.AccountViewImpl;
import com.java.view.specialty.SpecialtyViewImpl;

import java.util.*;

public class CustomerViewImpl implements CustomerView {
    private CustomerController customerController;
    private SpecialityController specialityController;
    private AccountController accountController;
    private Scanner sc;

    public CustomerViewImpl() {
        customerController = CustomerControllerImpl.getInstance();
        specialityController = SpecialityControllerImpl.getInstance();
        accountController = AccountControllerImpl.getInstance();
    }

    @Override
    public void main() {
        sc = new Scanner(System.in);
        int choice = 0;
        System.out.println("\n--Покупатели--");
        System.out.println("Выберите действие:");
        System.out.println("1: Создать покупателя");
        System.out.println("2: Посмотреть покупателей");
        System.out.println("3: Редактировать покупателя");
        System.out.println("4: Удалить покупателя");
        System.out.println("5: Назад");
        try {
            choice = sc.nextInt();
            while (true) {
                switch (choice) {
                    case 1 -> {
                        create();
                    }
                    case 2 -> {
                        get();
                    }
                    case 3 -> {
                        edit();
                    }
                    case 4 -> {
                        delete();
                    }
                    case 5 -> {
                        back();
                    }
                    default -> {
                        System.out.print("\nТакого действия нет");
                        main();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Введены неверные символы");
            main();
        }
    }

    @Override
    public void create() {
        System.out.println("Добавьте специальности");
        customerController.createCustomer(addSpecialties(), addAccount());
        main();
    }

    private List<Specialty> addSpecialties() {
        sc = new Scanner(System.in);
        List<Specialty> list = new ArrayList<>();
        long choice = 1;
        new SpecialtyViewImpl().getAll(); // Доступные специальности

        choice = sc.nextLong();
        while (choice != 0) {
            list.add(specialityController.getSpecialtyById(choice));
            System.out.println("Добавлена специальность "
                    + specialityController.getSpecialtyById(choice)
                    + " Размер: " + list.size());
            System.out.println("\'0\' для выхода");
            choice = sc.nextLong();
        }
        return list;
    }
    private Account addAccount() {
        System.out.println("Введите имя");
        String name = sc.next();
        Account account = new Account(name,AccountStatus.ACTIVE);
        accountController.createAccount(account);
        return account;
    }

    @Override
    public void get() {
        getAll();
        System.out.println("Для продолжения введите любой символ...");
        sc.next();
        main();
    }

    private void getAll() {
        System.out.println("--Список покупателей--");
        for (int i = 0; i < customerController.getAll().size(); i++) {
            System.out.println("id: " + (i + 1) + " | " + customerController.getAll().get(i) + " |");
        }
    }

    @Override
    public void edit() {
        sc = new Scanner(System.in);
        System.out.println("--Выберите покупателя для замены--");
        getAll();
        long choice = sc.nextLong();
        System.out.println("Введите новое имя: ");
        String name = sc.next();
        Set<Specialty> specialties = customerController.getCustomerById(choice).getSpecialties();
        System.out.println(specialties);
        Customer customer = new Customer(specialties,
                customerController.getCustomerById(choice).getAccount());
        customer.getAccount().setName(name);
        accountController.editAccount(customer.getAccount(),choice);
        customerController.editCustomer(customer,choice);
        main();
    }

    @Override
    public void delete() { // 15.10
        sc = new Scanner(System.in);
        System.out.println("Выберите покупателя для удаления:");
        getAll();
        long choice = sc.nextLong();
        customerController.deleteCustomerById(choice);
        accountController.deleteAccountById(choice);
        main();
    }

    @Override
    public void back() {
        View view = new ViewImpl();
        view.main();
    }
}
