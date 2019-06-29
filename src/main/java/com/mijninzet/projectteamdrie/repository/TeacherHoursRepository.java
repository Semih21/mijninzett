package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface TeacherHoursRepository extends JpaRepository<TeacherHours, Integer> {

    TeacherHours findByUserId(int userId);

    //Brahim code: update hours based on hoursleft, hoursused and teacherID
    @Modifying
    @Query(value = "UPDATE mijn_inzet.teacher_hours SET teaching_hours_left = :hoursLeft, teaching_hours_used = :hoursUsed WHERE (user_id = :teacherId) ", nativeQuery = true)
    @Transactional
    void updateTeacherHours(@Param("hoursLeft") Integer hoursLeft, @Param("hoursUsed") Integer hoursUsed,@Param("teacherId") Integer teacherId);

    //Brahim code: get hoursleft by teacherId
    @Query(value ="select teaching_hours_left from teacher_hours where user_id = :teacherId ", nativeQuery = true)
    int getHoursLeft(@Param("teacherId") Integer teacherId);

    //Brahim code: get hours used by teacherId
    @Query(value ="select teaching_hours_used from teacher_hours where user_id = :teacherId ", nativeQuery = true)
    int getHoursUsed(@Param("teacherId") Integer teacherId);
}
