package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContestDao extends JpaRepository<Contest, Long> {
    Optional<Contest> findByCode(String code);
    boolean existsByCode(String code);
}

