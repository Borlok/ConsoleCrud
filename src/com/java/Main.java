package com.java;

import com.java.model.Account;
import com.java.model.AccountStatus;
import com.java.view.View;
import com.java.view.ViewImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;

public class Main {

    public static void main(String[] args) {
        View view = new ViewImpl();
        view.main();

//        SpecialtyView view = new SpecialtyViewImpl();
//        view.main();

//        String x = "1|Юрий [2,3,1] ACTIVE";
//        String [] z = Arrays.stream(x.split("[^a-zA-ZА-Яа-я]"))
//                .filter(zx->!(zx.equals("")))
//            .toArray(String[]::new);
//        System.out.println(Arrays.toString(z));
//        System.out.println(AccountStatus.valueOf(z[1]));

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
