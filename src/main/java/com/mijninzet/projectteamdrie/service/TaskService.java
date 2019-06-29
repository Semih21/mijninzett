package com.mijninzet.projectteamdrie.service;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public void addNewTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task findById(int id){
        return taskRepository.findTaskByTaskId(id);
    }

}
