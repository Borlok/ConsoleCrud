package com.java;

import com.java.controllers.customer.CustomerControllerImpl;
import com.java.controllers.specialty.SpecialityController;
import com.java.controllers.specialty.SpecialityControllerImpl;
import com.java.model.Specialty;
import com.java.repository.customer.CustomerRepositoryImpl;
import com.java.view.customer.CustomerView;
import com.java.view.customer.CustomerViewImpl;
import com.java.view.View;
import com.java.view.ViewImpl;
import com.java.view.specialty.SpecialtyView;
import com.java.view.specialty.SpecialtyViewImpl;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        View view = new ViewImpl();
        view.main();
//        SpecialtyView view = new SpecialtyViewImpl();
//        view.main();
//        String x = "1|Юрий [3,1,2]";
//        String z = Arrays.stream(x.split("[^a-zA-ZА-Яа-я]")).reduce("",String::concat);
//        System.out.println(z);
//        File file = new File("customers.txt");
//        Set<Specialty> set = new HashSet<>();
//        try (Scanner sc = new Scanner(file)) { // ПРобуем считать ID customer
//            while (sc.hasNext()) {
//                String customer = sc.nextLine();
//                String [] spec = Arrays.stream(customer.split("\\D")).skip(1).filter(x -> !(x.equals(""))).toArray(String[]::new);
//                System.out.println(Arrays.toString(spec));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}
