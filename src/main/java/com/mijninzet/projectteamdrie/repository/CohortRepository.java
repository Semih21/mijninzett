package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import org.joda.time.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Integer> {

    List<Cohort> findAll();
    List<Cohort> findAllByCohortIdAfter(int cohortId);
    Cohort getByCohortId(int cohortId);

    @Query(value = "SELECT cohort_id FROM mijn_inzet.cohort", nativeQuery = true)
    List<Integer> getCohortIds();









}
