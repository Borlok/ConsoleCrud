package com.java.repository.specialty;


import com.java.model.Specialty;
import com.java.repository.account.AccountRepositoryImpl;

import java.io.*;
import java.util.*;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    private static SpecialtyRepositoryImpl repository;
    private List<Specialty> specialties;
    private File file = new File("specialties.txt");

    private SpecialtyRepositoryImpl() {
    }

    @Override
    public void create(Specialty specialty) {
        specialties = getAll();
        try (PrintWriter writer = new PrintWriter("specialties.txt")) {
            specialties.add(specialty);
//            System.out.println(Arrays.toString(specialties.toArray()));//
            for (int i = 0; i < specialties.size(); i++) {
                writer.println((i + 1) + "|" + specialties.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try (PrintWriter writer = new PrintWriter("specialties.txt")) {
            for (int i = 0; i < specialties.size(); i++) {
                writer.println((i + 1) + "|" + specialties.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Specialty getById(Long id) {
        return getAll().get((int) (id - 1));
    }

    @Override
    public void update(Specialty specialty, Long id) {
        specialties = getAll();
        specialties.set((int) (id - 1),specialty);
        create();
    }

    @Override
    public void delete(Long id) {
        specialties = getAll();
        specialties.set((int) (id - 1),null);
        create();
    }

    @Override
    public List<Specialty> getAll() {
        specialties = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileInputStream(file))) {
            while (sc.hasNext()) {
                specialties.add(new Specialty(sc.skip("\\d\\|").nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return specialties;
    }

    public static SpecialtyRepositoryImpl getInstance() {
        if (repository == null) {
            repository = new SpecialtyRepositoryImpl();
        }
        return repository;
    }
}
