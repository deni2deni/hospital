package com.academy.model.repository;

import com.academy.model.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {

    List<Journal> findAllByPatientId(Integer id);

}
