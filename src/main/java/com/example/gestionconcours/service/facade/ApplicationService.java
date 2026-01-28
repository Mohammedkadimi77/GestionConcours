package com.example.gestionconcours.service.facade;

import com.example.gestionconcours.entity.Application;

import java.util.List;

public interface ApplicationService {
    Application createDraft(Long candidateId, Long contestId, Long trackId);
    Application submit(Long applicationId);

    Application findById(Long id);
    Application findByApplicationNumber(String applicationNumber);

    List<Application> findByCandidateId(Long candidateId);
    List<Application> findByContestId(Long contestId);
    List<Application> findByContestIdAndStatus(Long contestId, String status);
}

