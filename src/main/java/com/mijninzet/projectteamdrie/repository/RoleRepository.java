package com.mijninzet.projectteamdrie.repository;


import com.mijninzet.projectteamdrie.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String role);

    //Get roles belonging to a user_id
    @Query(value= "SELECT R.role_name FROM mijn_inzet.role R JOIN user_role UR ON R.role_id=UR.role_id where UR.user_id= :userId", nativeQuery = true)
    String[] findRoleNameByUserId(@Param("userId") Integer userID);

}
