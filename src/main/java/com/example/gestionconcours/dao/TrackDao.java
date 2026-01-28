package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackDao extends JpaRepository<Track, Long> {
    List<Track> findByContestId(Long contestId);
}

