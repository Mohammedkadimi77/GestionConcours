package com.example.gestionconcours.service.impl;

import com.example.gestionconcours.dao.ApplicationDao;
import com.example.gestionconcours.dao.CandidateDao;
import com.example.gestionconcours.dao.ContestDao;
import com.example.gestionconcours.dao.TrackDao;
import com.example.gestionconcours.entity.Application;
import com.example.gestionconcours.entity.Candidate;
import com.example.gestionconcours.entity.Contest;
import com.example.gestionconcours.entity.Track;
import com.example.gestionconcours.service.facade.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDao applicationDao;
    private final CandidateDao candidateDao;
    private final ContestDao contestDao;
    private final TrackDao trackDao;

    public ApplicationServiceImpl(ApplicationDao applicationDao, CandidateDao candidateDao,
                                  ContestDao contestDao, TrackDao trackDao) {
        this.applicationDao = applicationDao;
        this.candidateDao = candidateDao;
        this.contestDao = contestDao;
        this.trackDao = trackDao;
    }

    @Override
    @Transactional
    public Application createDraft(Long candidateId, Long contestId, Long trackId) {
        Candidate candidate = candidateDao.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found: " + candidateId));

        Contest contest = contestDao.findById(contestId)
                .orElseThrow(() -> new RuntimeException("Contest not found: " + contestId));

        Track track = trackDao.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found: " + trackId));

        // Prevent duplicate application per contest per candidate
        if (applicationDao.existsByContestIdAndCandidateId(contestId, candidateId)) {
            throw new RuntimeException("You already have an application for this contest.");
        }

        Application app = new Application();
        app.setCandidate(candidate);
        app.setContest(contest);
        app.setTrack(track);
        app.setStatus("DRAFT");
        app.setTotalScore(0.0);
        app.setRanking(null);
        app.setSubmittedAt(null);
        app.setApplicationNumber(null);

        return applicationDao.save(app);
    }

    @Override
    @Transactional
    public Application submit(Long applicationId) {
        Application app = findById(applicationId);

        if (!"DRAFT".equals(app.getStatus())) {
            throw new RuntimeException("Only DRAFT applications can be submitted.");
        }

        // Here: normally you verify required documents, etc.
        // (we will add it later with DocumentService)

        app.setStatus("SUBMITTED");
        app.setSubmittedAt(LocalDateTime.now());

        // Temporary simple number generation (we will improve with a sequence later)
        String number = "APP-" + app.getContest().getYear() + "-" + app.getId();
        app.setApplicationNumber(number);

        return applicationDao.save(app);
    }

    @Override
    public Application findById(Long id) {
        return applicationDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found: " + id));
    }

    @Override
    public Application findByApplicationNumber(String applicationNumber) {
        return applicationDao.findByApplicationNumber(applicationNumber)
                .orElseThrow(() -> new RuntimeException("Application not found with number: " + applicationNumber));
    }

    @Override
    public java.util.List<Application> findByCandidateId(Long candidateId) {
        return applicationDao.findByCandidateId(candidateId);
    }

    @Override
    public java.util.List<Application> findByContestId(Long contestId) {
        return applicationDao.findByContestId(contestId);
    }

    @Override
    public java.util.List<Application> findByContestIdAndStatus(Long contestId, String status) {
        return applicationDao.findByContestIdAndStatus(contestId, status);
    }
}

