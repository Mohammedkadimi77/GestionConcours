package com.example.gestionconcours.dao;

import com.example.gestionconcours.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentDao extends JpaRepository<Document, Long> {
    List<Document> findByApplicationId(Long applicationId);
    List<Document> findByApplicationIdAndType(Long applicationId, String type);
}

