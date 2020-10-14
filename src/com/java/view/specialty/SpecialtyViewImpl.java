package com.java.view.specialty;

import com.java.controllers.specialty.SpecialityController;
import com.java.controllers.specialty.SpecialityControllerImpl;
import com.java.model.Specialty;
import com.java.view.ViewImpl;

import java.util.Arrays;
import java.util.Scanner;

public class SpecialtyViewImpl implements SpecialtyView{
    private SpecialityController specialityController;
    private Scanner sc;

    public SpecialtyViewImpl() {
        specialityController = SpecialityControllerImpl.getInstance();
    }

    @Override
    public void main() {
        sc = new Scanner(System.in);
        int choice = 0;
        System.out.println("\n--Специальности--");
        System.out.println("Выберите действие:");
        System.out.println("1: Добавить специальность");
        System.out.println("2: Посмотреть специальности");
        System.out.println("3: Редактировать специальность");
        System.out.println("4: Удалить специальность");
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
        sc = new Scanner(System.in);
        System.out.println("Введите специальность");
        String name = sc.next();
        specialityController.createSpecialty(new Specialty(name));
        main();
    }

    @Override
    public void get() {
        getAll();
        System.out.println("Для продолжения введите любой символ...");
        sc.next();
        main();
    }

    public void getAll() {
        System.out.println("--Список специальностей--");
        for (int i = 0; i < specialityController.getAll().size(); i++)
            System.out.println("id: " + (i+1) + " | " + specialityController.getAll().get(i) + " |");
    }

    @Override
    public void edit() {
        sc = new Scanner(System.in);
        System.out.println("--Выберите специальность для замены--");
        getAll();
        long choice = sc.nextLong();
        System.out.println("Введите новую специальность: ");
        String name = sc.next();
        Specialty specialty = new Specialty(name);
        specialityController.editSpecialty(specialty,choice);
        main();
    }

    @Override
    public void delete() {
        sc = new Scanner(System.in);
        System.out.println("Выберите специальность для удаления:");
        getAll();
        specialityController.deleteSpecialtyById(sc.nextLong());
        main();
    }

    public void back() {
        new ViewImpl().main();
    }
}
