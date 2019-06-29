package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Exception;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ExceptionRepository extends CrudRepository<Exception,Integer>{
    List<Exception> findAll();
    List<Exception> findByUser_Id(int id);

    //Brahim code: get incident by given date and user_id
    @Query(value = "SELECT IE.color_option FROM mijn_inzet.exception IE " +
            "where IE.user_id=:teacherId AND :dayDate BETWEEN IE.start_date AND IE.end_date", nativeQuery = true)
    String getIncident(@Param("teacherId") Integer teacherId, @Param("dayDate") LocalDate dayDate);


}
