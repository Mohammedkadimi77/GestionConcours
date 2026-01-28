package com.example.gestionconcours.service.facade;

import com.example.gestionconcours.entity.Track;

import java.util.List;

public interface TrackService {
    Track save(Track track);
    Track findById(Long id);
    List<Track> findByContestId(Long contestId);
    int deleteById(Long id);
}
