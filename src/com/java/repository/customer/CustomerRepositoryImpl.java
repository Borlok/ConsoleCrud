package com.java.repository.customer;

import com.java.model.Account;
import com.java.model.AccountStatus;
import com.java.model.Customer;
import com.java.model.Specialty;
import com.java.repository.account.AccountRepositoryImpl;
import com.java.repository.specialty.SpecialtyRepositoryImpl;

import java.io.*;
import java.util.*;

public class CustomerRepositoryImpl implements CustomerRepository{
    private static CustomerRepositoryImpl customerRepository;
    private static SpecialtyRepositoryImpl specialtyRepository = SpecialtyRepositoryImpl.getInstance();
    private static AccountRepositoryImpl accountRepository = AccountRepositoryImpl.getInstance();
    private List<Customer> customers;
    private List<Integer> specialties;
    private File file = new File("customers.txt");
//    private String name;


    @Override
    public void create(Customer customer) {
//        System.out.println("Тут1");//
        customers = getAll();
//        System.out.println("Тут2");//
        try (PrintWriter writer = new PrintWriter("customers.txt")) {
            customers.add(customer);
//            System.out.println(customers);//
//            System.out.println("Тут3");//
//            System.out.println(customers);//
            for (int i = 0; i < customers.size(); i++) {
                writer.print((i + 1) + "|" + customers.get(i) + " [");
                specialties = getSpecialtiesId(customers.get(i));
//                System.out.println(specialties);//
//                System.out.println("Тут4");//
                for (int j = 0; j < customers.get(i).getSpecialties().size(); j++) {
                    if (j != 0 && j != customers.get(i).getSpecialties().size())
                        writer.print(",");
                    writer.print(specialties.get(j));
                }
                writer.println("] " + customer.getAccount().getStatus()); //14.10 12
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> getSpecialtiesId(Customer customer) {
        List<Specialty> customerSpecialties = new ArrayList<>(customer.getSpecialties());
        List<Specialty> repositorySpecialties = specialtyRepository.getAll();
        specialties = new ArrayList<>();
//        System.out.println(customerSpecialties);//
//        System.out.println(repositorySpecialties);//
        for (int i = 0; i < customerSpecialties.size(); i++) {
            for (int j = 0; j < repositorySpecialties.size(); j++) {
                if (customerSpecialties.get(i).getName().equals(repositorySpecialties.get(j).getName()))
                    specialties.add(j+1);
            }
        }
        return specialties;
    }

    public void createWithoutNewCustomer() {
        try (PrintWriter writer = new PrintWriter("customers.txt")) {
            for (int i = 0; i < customers.size(); i++) {
                writer.print((i + 1) + "|" + customers.get(i) + " [");
                specialties = getSpecialtiesId(customers.get(i));
//                System.out.println(specialties);//
//                System.out.println("Тут4");//
                for (int j = 0; j < customers.get(i).getSpecialties().size(); j++) {
                    if (j != 0 && j != customers.get(i).getSpecialties().size())
                        writer.print(",");
                    writer.print(specialties.get(j));
                }
                writer.println("] " + customers.get(i).getAccount().getStatus()); //14.10 12
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getById(Long id) {
        return getAll().get((int) (id - 1));
    }

    @Override
    public void update(Customer customer, Long id) {
        customers = getAll();
        customer.getAccount().setStatus(AccountStatus.ACTIVE);
        customers.set((int) (id - 1),customer);
        createWithoutNewCustomer();
    }

    @Override
    public void delete(Long id) {
        customers = getAll();
        customers.set((int) (id - 1),new Customer(new HashSet<>(),new Account(null,AccountStatus.DELETED)));
        createWithoutNewCustomer();
    }

    @Override
    public List<Customer> getAll() { // Это нужно в первую очередь
        customers = new ArrayList<>();
//        System.out.println("Здесь");//
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                String customer = sc.nextLine();
                String [] split = Arrays.stream(customer.split("[^a-zA-ZА-Яа-я]"))
                        .filter(zx->!(zx.equals("")))
                        .toArray(String[]::new);
                String name = split[0];
                AccountStatus status = AccountStatus.valueOf(split[1]);
                Set<Specialty> spec = getSpecialties(customer);
//                System.out.println("Здесь " + spec);//
                customers.add(new Customer(spec, new Account(name,status)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("Здесь");//
        return customers;
    }

    private Set<Specialty> getSpecialties(String customer) {
        Set<Specialty> special = new HashSet<>();
//            System.out.println(name);//
            String[] spec = Arrays.stream(customer.split("\\D")).skip(1).filter(x -> !(x.equals(""))).toArray(String[]::new);
            for (String x : spec) {
                special.add(specialtyRepository.getById(Long.parseLong(x)));
            }

        return special;
    }

    private CustomerRepositoryImpl() {

    }

    public static CustomerRepositoryImpl getInstance() {
        if (customerRepository == null)
            customerRepository = new CustomerRepositoryImpl();
        return customerRepository;
    }

}
