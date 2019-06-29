package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CohortService {
    private final CohortRepository cohortRepository;

    public CohortService(CohortRepository cohortRepository) {
        this.cohortRepository = cohortRepository;
    }

    public List<Cohort> findAll(){
        return new ArrayList<>(cohortRepository.findAll());
    }

    public List<Integer> getCohortIds(){
        return cohortRepository.getCohortIds();
    }
}
