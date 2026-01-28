package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateDao extends JpaRepository<Candidate, Long> {
    Candidate findByCin(String cin);
    Candidate findByUserId(Long userId);
    boolean existsByCin(String cin);
}
