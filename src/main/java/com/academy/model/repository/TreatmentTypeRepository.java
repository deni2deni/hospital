package com.academy.model.repository;

import com.academy.model.entity.TreatmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentTypeRepository extends JpaRepository<TreatmentType, Integer> {

    TreatmentType findByName(String name);
}
