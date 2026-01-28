package com.example.gestionconcours.service.impl;

import com.example.gestionconcours.dao.ContestDao;
import com.example.gestionconcours.dao.TrackDao;
import com.example.gestionconcours.entity.Track;
import com.example.gestionconcours.service.facade.TrackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackDao trackDao;
    private final ContestDao contestDao;

    public TrackServiceImpl(TrackDao trackDao, ContestDao contestDao) {
        this.trackDao = trackDao;
        this.contestDao = contestDao;
    }

    @Override
    public Track save(Track track) {
        if (track == null) return null;
        if (track.getContest() == null || track.getContest().getId() == null) {
            throw new RuntimeException("Track must be linked to a contest (contest.id required).");
        }
        // Validate contest exists
        contestDao.findById(track.getContest().getId())
                .orElseThrow(() -> new RuntimeException("Contest not found with id: " + track.getContest().getId()));
        return trackDao.save(track);
    }

    @Override
    public Track findById(Long id) {
        return trackDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Track not found with id: " + id));
    }

    @Override
    public List<Track> findByContestId(Long contestId) {
        return trackDao.findByContestId(contestId);
    }

    @Override
    public int deleteById(Long id) {
        if (!trackDao.existsById(id)) return 0;
        trackDao.deleteById(id);
        return 1;
    }
}
