package com.mijninzet.projectteamdrie.repository;


import com.mijninzet.projectteamdrie.model.entity.TaskApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;



@Repository
public interface TaskApplicationRepository extends JpaRepository<TaskApplication, Integer> {

    // store data into the task_application tabel
    @Modifying
    @Query(value = "INSERT INTO task_application (user_id, application_date,available_hours,role," +
            "unsubcribe_date,task_task_id)  " +
            "VALUES (:userId,:applicationDate,:availableHours,:role,:unsubcribeDate,:taskID)", nativeQuery = true)
    @Transactional
    void insertTaskapplication(@Param("userId") Integer userId, @Param("applicationDate") LocalDate applicationDate,
                               @Param("unsubcribeDate") LocalDate unsubcribeDate,
                               @Param("availableHours") Integer availableHours, @Param("role") String role,
                               @Param("taskID") Integer taskID);

    // update query: obv user_id, task_id en opgegeven uren
    @Modifying
    @Query(value = "UPDATE task_application TA SET TA.available_hours = :availableHours WHERE TA.user_id=:userId " +
            "AND TA.task_task_id =:taskId ", nativeQuery = true)
    @Transactional
    void updateHours(@Param("availableHours") Integer availableHours,
                     @Param("userId") Integer userId, @Param("taskId") Integer taskId);

    // remove query: obv user_id en task_id
    @Modifying
    @Query(value = "DELETE FROM task_application WHERE task_task_id = :taskId AND user_id= :userId ",nativeQuery = true)
    @Transactional
    void deleteApplication(@Param("taskId") Integer taskId, @Param("userId") Integer userId);


    // retrieve data from multiple tables by means of inner joins
    @Query(value = "SELECT T.task_id AS taskId, T.task_name AS taskName, T.estimated_hours AS estHours, " +
            "CONCAT(U.first_name, ' ', U.last_name) AS fullName " +
            ",TA.application_date AS applDate, TA.available_hours AS availHours  " +
            "FROM task_application TA JOIN task T ON (TA.task_task_id= T.task_id )" +
            " JOIN user U ON (TA.user_id=U.user_id) WHERE U.user_id= :userId",nativeQuery =true)
    ArrayList<Object[]> getApplicationOverview(@Param("userId") Integer userId);

    @Modifying
    @Query(value = "DELETE FROM mijn_inzet.task_application  WHERE task_task_id = :taskId", nativeQuery = true)
    @Transactional
    void deleteTaskEnTaskApplication(@Param("taskId") Integer taskId);




}
