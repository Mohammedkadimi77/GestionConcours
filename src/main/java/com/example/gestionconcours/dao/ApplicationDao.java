package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao extends JpaRepository<Application, Long> {
    Application findByApplicationNumber(String applicationNumber);

    List<Application> findByCandidateId(Long candidateId);

    List<Application> findByContestId(Long contestId);

    List<Application> findByContestIdAndStatus(Long contestId, String status);

    boolean existsByContestIdAndCandidateId(Long contestId, Long candidateId);
}
