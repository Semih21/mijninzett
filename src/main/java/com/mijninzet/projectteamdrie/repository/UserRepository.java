package com.mijninzet.projectteamdrie.repository;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
   // User findByEmailIdIgnoreCase(String emailId);

    User findUserById(int id);

    //Brahim Code: get user_id by email from login
    @Query(value="SELECT user_id FROM mijn_inzet.user where user.email=:email", nativeQuery = true)
    Integer getIdLoggedInUser(@Param("email") String emailUser);


    //Brahim Code: get all teachers Names by Role=teacher
    @Query(value="SELECT * FROM mijn_inzet.user WHERE user_id IN (select user_id FROM mijn_inzet.user_role WHERE role_id=2);", nativeQuery = true)
    ArrayList<Object[]> getTeachers();



}
