package com.example.gestionconcours.service.facade;

import com.example.gestionconcours.entity.Contest;

import java.util.List;

public interface ContestService {
    Contest save(Contest contest);
    Contest findById(Long id);
    Contest findByCode(String code);
    List<Contest> findAll();
    int deleteById(Long id);
}

