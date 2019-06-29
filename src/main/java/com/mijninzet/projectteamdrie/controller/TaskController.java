package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Task;
import com.mijninzet.projectteamdrie.repository.TaskApplicationRepository;
import com.mijninzet.projectteamdrie.repository.TaskRepository;
import com.mijninzet.projectteamdrie.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskApplicationRepository taskApplicationRepository;


    @RequestMapping(value = "/showTasks")
    public String makeVacancyList(Model model) {
        model.addAttribute("showTasks", taskRepository.getVacancies());
        return "showTasks";
    }

    @RequestMapping("/taskList")
    public String showTaskList(Model model){
        List<Task>tasks=taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "task/task-list";
    }

    @RequestMapping("/task/newTask")
    public String addTask(Model model){
        Task task = new Task();
        model.addAttribute("task",task);
        return "task/task-form";
    }
    @PostMapping("/task/taskSave")
    public String addTask(@ModelAttribute ("task") Task task){
        taskService.addNewTask(task);
        return ("redirect:/taskList");
    }


    @GetMapping("/task/updateTask")
    public String updateSubject(@RequestParam("taskId") int taskId, Model model){
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "task/task-form";
    }

    @GetMapping("task/deleteTask")
    public String deleteSubject(@RequestParam("taskId") int taskId, Model model) {
        taskApplicationRepository.deleteTaskEnTaskApplication(taskId);
        taskService.deleteTask(taskId);
        return ("redirect:/taskList");
    }
}

