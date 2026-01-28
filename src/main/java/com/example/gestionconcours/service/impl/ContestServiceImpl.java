package com.example.gestionconcours.service.impl;

import com.example.gestionconcours.dao.ContestDao;
import com.example.gestionconcours.entity.Contest;
import com.example.gestionconcours.service.facade.ContestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestServiceImpl implements ContestService {

    private final ContestDao contestDao;

    public ContestServiceImpl(ContestDao contestDao) {
        this.contestDao = contestDao;
    }

    @Override
    public Contest save(Contest contest) {
        if (contest == null) return null;
        if (contest.getCode() != null && contestDao.existsByCode(contest.getCode())) {
            throw new RuntimeException("Contest code already exists: " + contest.getCode());
        }
        if (contest.getStatus() == null) contest.setStatus("DRAFT");
        return contestDao.save(contest);
    }

    @Override
    public Contest findById(Long id) {
        return contestDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Contest not found with id: " + id));
    }

    @Override
    public Contest findByCode(String code) {
        return contestDao.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Contest not found with code: " + code));
    }

    @Override
    public List<Contest> findAll() {
        return contestDao.findAll();
    }

    @Override
    public int deleteById(Long id) {
        if (!contestDao.existsById(id)) return 0;
        contestDao.deleteById(id);
        return 1;
    }
}
