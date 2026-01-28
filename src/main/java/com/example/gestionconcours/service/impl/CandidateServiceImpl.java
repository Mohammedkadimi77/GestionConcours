package com.example.gestionconcours.service.impl;

import com.example.gestionconcours.dao.CandidateDao;
import com.example.gestionconcours.entity.Candidate;
import com.example.gestionconcours.service.facade.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateDao candidateDao;

    public CandidateServiceImpl(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }

    @Override
    public Candidate save(Candidate candidate) {
        if (candidate == null) return null;

        if (candidate.getCin() != null && candidateDao.existsByCin(candidate.getCin())) {
            throw new RuntimeException("CIN already exists: " + candidate.getCin());
        }
        return candidateDao.save(candidate);
    }

    @Override
    public Candidate findById(Long id) {
        return candidateDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
    }

    @Override
    public Candidate findByCin(String cin) {
        return candidateDao.findByCin(cin)
                .orElseThrow(() -> new RuntimeException("Candidate not found with cin: " + cin));
    }

    @Override
    public Candidate findByUserId(Long userId) {
        return candidateDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with userId: " + userId));
    }

    @Override
    public List<Candidate> findAll() {
        return candidateDao.findAll();
    }
}
