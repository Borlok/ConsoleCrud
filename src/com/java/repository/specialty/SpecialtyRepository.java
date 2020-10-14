package com.java.repository.specialty;

import com.java.model.Specialty;
import com.java.repository.GenericRepository;

import java.util.List;
import java.util.Set;

public interface SpecialtyRepository extends GenericRepository<Specialty,Long> {
    List<Specialty> getAll();
}
