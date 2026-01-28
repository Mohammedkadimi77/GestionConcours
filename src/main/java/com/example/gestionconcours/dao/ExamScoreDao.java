package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.ExamScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamScoreDao extends JpaRepository<ExamScore, Long> {

    List<ExamScore> findByExamId(Long examId);

    List<ExamScore> findByApplicationId(Long applicationId);

    ExamScore findByExamIdAndApplicationId(Long examId, Long applicationId);
}

