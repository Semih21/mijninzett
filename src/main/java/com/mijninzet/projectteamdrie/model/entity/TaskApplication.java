package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.Task;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
@Entity
public class TaskApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private LocalDate applicationDate;
    private LocalDate unsubcribeDate;
    private int availableHours;
    private String role;
//    private int task_task_id;

    @ManyToOne
    @JoinColumn(name="task_task_id" )
    private Task task;

    public TaskApplication() {
        this.userId = userId;
        this.applicationDate = LocalDate.now();
        this.unsubcribeDate = unsubcribeDate;
        this.availableHours = availableHours;
        this.role = role;
//        this.task_task_id=0;
    }

    public TaskApplication( int userId, LocalDate applicationDate, LocalDate unsubcribeDate, int availableHours, String role, int task_task_id) {

        this.userId = userId;
        this.applicationDate = LocalDate.now();
        this.unsubcribeDate = unsubcribeDate;
        this.availableHours = availableHours;
        this.role = role;
//        this.task_task_id=task_task_id;

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getUnsubcribeDate() {
        return unsubcribeDate;
    }

    public void setUnsubcribeDate(LocalDate unsubcribeDate) {
        this.unsubcribeDate = unsubcribeDate;
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(int availableHours) {
        this.availableHours = availableHours;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
