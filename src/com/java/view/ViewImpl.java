package com.java.view;

import com.java.view.account.AccountViewImpl;
import com.java.view.customer.CustomerViewImpl;
import com.java.view.specialty.SpecialtyViewImpl;

import java.util.Scanner;

public class ViewImpl implements View{
    private View customers;
    private View account;
    private View specialty;

    public ViewImpl() {
        customers = new CustomerViewImpl();
        account = new AccountViewImpl();
        specialty = new SpecialtyViewImpl();
    }

    @Override
    public void main() {
        try (Scanner sc = new Scanner(System.in)) {
            int order = 0;
            System.out.println("\n--Меню--");
            System.out.println("Добро пожаловать, выберите вариант");
            while (true) {
                System.out.println("1: Покупатели");
                System.out.println("2: Аккаунт");
                System.out.println("3: Специальность");
                System.out.println("4: Выход");
                order = sc.nextInt();
                switch (order) {
                    case 1:
                        customers.main();
                        break;
                    case 2:
                        account.main();
                        break;
                    case 3:
                        specialty.main();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Такой команды не существует");
                        break;
                }
            }
        }
    }
}
