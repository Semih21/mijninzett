package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT subject_id FROM mijn_inzet.subject where subject_name = :subject_name", nativeQuery = true)
    int getSubjectIdByName(@Param("subject_name")String subject_name);

    //Brahim Code: get all subject names
    @Query(value="SELECT * FROM mijn_inzet.subject;", nativeQuery = true)
    ArrayList<Object[]> getSubjects();

   //Brahim Code: get list of teacher/subject preferences
    @Query(value="SELECT * FROM mijn_inzet.subject_preference;", nativeQuery = true)
    ArrayList<Object[]> getPreferences();

    //Brahim Code: get teacher with single teacher preference for one subject
    @Query(value="SELECT preference FROM mijn_inzet.subject_preference where user_user_id=:userId AND subject_subject_id=:subjectId ", nativeQuery = true)
    String getSingleTeacherSubjectPref(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);

    Subject getBySubjectId(int subjectId);
    Subject findSubjectBySubjectId(int id);





}
