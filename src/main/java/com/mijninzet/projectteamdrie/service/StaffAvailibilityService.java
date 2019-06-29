package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import com.mijninzet.projectteamdrie.repository.StaffAvailibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffAvailibilityService {
    @Autowired
    private StaffAvailibilityRepository staffAvailibilityRepository;

    private List<StaffAvailability> staffAvailabilities;
    private StaffAvailability staffAvailability;

    public List<StaffAvailability> getAll(){
        ArrayList<StaffAvailability> sa = new ArrayList<>();
        staffAvailibilityRepository.findAll()
                .forEach(sa::add);
        return sa;
    }

    public List<Integer> getAllCohorts(){
        List<Integer>cohorts=new ArrayList<>();
        final int userId = UserSingleton.getInstance().getId();
        cohorts = staffAvailibilityRepository.getCohorts(userId);
        return cohorts;
    }

    public List<StaffAvailability> findStaffAvailibilityByUseridAndCohort(int userId, int cohort){
        List<StaffAvailability> staffAvailabilities=new ArrayList<>();
        staffAvailibilityRepository.findByUser_IdAndCohort_CohortId(userId,cohort)
                .forEach(staffAvailabilities::add);
        return staffAvailabilities;
    }

    public StaffAvailability findById(int id){
        StaffAvailability sa = new StaffAvailability();
        sa=staffAvailibilityRepository.findById(id);
        return sa;
    }

    public List<StaffAvailability>findByUserId(int id){
        List<StaffAvailability> staffAvailabilities=new ArrayList<>();
        staffAvailibilityRepository.findByUserId(id)
                .forEach(staffAvailabilities::add);
        return staffAvailabilities;
    }
    public void addStaffAvailibility(StaffAvailability sa){
        staffAvailibilityRepository.save(sa);

    }
    public boolean checkExistance(int id){
        return staffAvailibilityRepository.existsById(id);
    }





}

