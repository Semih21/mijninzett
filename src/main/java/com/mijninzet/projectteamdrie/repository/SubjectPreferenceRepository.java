package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.SubjectPreference;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubjectPreferenceRepository extends JpaRepository<SubjectPreference, Integer> {
    @Modifying
    @Query(value = "INSERT INTO `mijn_inzet`.`subject_preference` (`preference`, `subject_subject_id`, `user_user_id`) " +
            "VALUES (:preference,:subject_id,:user_id)", nativeQuery = true)

    @Transactional
    void insertPreference(@Param("preference") Integer preference,
                          @Param("subject_id") Integer subject_id,
                          @Param("user_id") Integer user_id);

    @Modifying
    @Query(value = "UPDATE mijn_inzet.subject_preference SET preference = :preference where subject_subject_id =:subject_id AND user_user_id = :userid", nativeQuery = true)

    @Transactional
    void updatePreference(@Param("preference") Integer preference,
                          @Param("subject_id") Integer subject_id,
                          @Param("userid") Integer userid);


    @Query(value = "select preference from mijn_inzet.subject_preference Where subject_subject_id = :subjectId And user_user_id = :userId", nativeQuery = true)

    @Transactional
    Integer getPreferenceBySubjectIdAndUserId(@Param("subjectId") Integer subjectId,
                                             @Param("userId") Integer userId);


}