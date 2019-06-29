package com.mijninzet.projectteamdrie.repository;


import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface StaffAvailibilityRepository extends CrudRepository<StaffAvailability, Integer> {

    List<StaffAvailability> findByUser_IdAndCohort_CohortId(int id, int cohort);

    StaffAvailability findById(int id);

    List<StaffAvailability> findByUserId(int id);


    @Query(value = "SELECT cohort FROM mijn_inzet.staff_availability WHERE user_id=:teacherId", nativeQuery = true)
    List<Integer> getCohorts(@Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability maandagOchtend
    @Query(value = "SELECT maandag_ochtend FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getMondayMorning(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT maandag_middag FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getMondayNoon(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT dinsdag_ochtend FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getTuesdayMorning(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT dinsdag_middag FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getTuesdayNoon(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT woensdag_ochtend FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getWednesdayMorning(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT woensdag_middag FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getWednesdayNoon(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT donderdag_ochtend FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getThursdayMorning(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT donderdag_middag FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getThursdayNoon(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT vrijdag_ochtend FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getFridayMorning(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);

    //Brahim code: get specific Day/daypart availability
    @Query(value = "SELECT vrijdag_middag FROM mijn_inzet.staff_availability WHERE cohort=:cohortId AND user_id=:teacherId", nativeQuery = true)
    String getFridayNoon(@Param("cohortId") Integer cohortId, @Param("teacherId") Integer teacherId);



//
//    List<StaffAvailability> findAll();
//
//    List<StaffAvailability> findAllByCohort(String cohort);
//
//    List<StaffAvailability> findAllByUserIdAndCohort(int id, int cohort);
//


}

