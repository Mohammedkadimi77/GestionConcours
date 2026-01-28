package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamDao extends JpaRepository<Exam, Long> {
    List<Exam> findByContestId(Long contestId);
}

