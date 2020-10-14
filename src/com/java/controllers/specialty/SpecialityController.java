package com.java.controllers.specialty;

import com.java.model.Customer;
import com.java.model.Specialty;

import java.util.List;
import java.util.Set;

public interface SpecialityController {
    void createSpecialty (Specialty specialty);
    Specialty getSpecialtyById(long id);
    List<Specialty> getAll();
    void editSpecialty(Specialty specialty, long id);
    void deleteSpecialtyById(long id);
}
