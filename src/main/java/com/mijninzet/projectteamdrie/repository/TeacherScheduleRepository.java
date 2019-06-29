package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.TeacherSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherScheduleRepository extends JpaRepository<TeacherSchedule, Integer> {

    List<TeacherSchedule> getAllByCohortId(int cohortId);


}
