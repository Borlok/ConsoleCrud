package com.java.controllers.specialty;

import com.java.model.Specialty;
import com.java.repository.specialty.SpecialtyRepository;
import com.java.repository.specialty.SpecialtyRepositoryImpl;

import java.util.List;

public class SpecialityControllerImpl implements SpecialityController{
    private static SpecialityControllerImpl controller;
    private SpecialtyRepository repository;
    private static Long ID_COUNT = Long.valueOf(0);

    private SpecialityControllerImpl() {
        repository = SpecialtyRepositoryImpl.getInstance();
    }

    public static SpecialityControllerImpl getInstance() {
        if (controller == null) {
            controller = new SpecialityControllerImpl();
        }
        return controller;
    }

    @Override
    public void createSpecialty(Specialty specialty) {
        repository.create(specialty);
    }

    @Override
    public Specialty getSpecialtyById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return repository.getAll();
    }

    @Override
    public void editSpecialty(Specialty specialty, long id) {
        repository.update(specialty,id);
    }

    @Override
    public void deleteSpecialtyById(long id) {
        repository.delete(id);
    }
}
