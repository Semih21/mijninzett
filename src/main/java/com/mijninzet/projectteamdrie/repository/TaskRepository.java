package com.mijninzet.projectteamdrie.repository;
import com.mijninzet.projectteamdrie.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{

 @Query(value="SELECT * from task WHERE task_id NOT IN (SELECT task_task_id from task_application);",
         nativeQuery = true)
 List<Task> getVacancies();

 Task findTaskByTaskId(int id);

 }
