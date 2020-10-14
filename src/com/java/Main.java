package com.java;

import com.java.view.View;
import com.java.view.ViewImpl;

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
