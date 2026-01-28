package com.example.gestionconcours.service.facade;

import com.example.gestionconcours.entity.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate save(Candidate candidate);
    Candidate findById(Long id);
    Candidate findByCin(String cin);
    Candidate findByUserId(Long userId);
    List<Candidate> findAll();
}

