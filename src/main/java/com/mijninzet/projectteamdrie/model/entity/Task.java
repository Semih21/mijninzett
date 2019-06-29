package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int taskId;
    private String taskName;
    private int estimatedHours;
    private int yearsToExpiryDate;


    public Task() {
    }

    public Task(int taskId, String taskName, int estimatedHours, int yearsToExpiryDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.estimatedHours = estimatedHours;
        this.yearsToExpiryDate = yearsToExpiryDate;
    }

    public Task(int taskId) {
        this.taskId=taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public int getYearsToExpiryDate() {
        return yearsToExpiryDate;
    }

    public void setYearsToExpiryDate(int yearsToExpiryDate) {
        this.yearsToExpiryDate = yearsToExpiryDate;
    }
}
