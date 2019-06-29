package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.*;

@Entity
public class TeacherHours {

    @Id
    private int userId;

    private double hoursTotal;

    private double teachingHoursUsed;

    private double teachingHoursLeft;

    public TeacherHours(int userId, double hoursTotal, double teachingHoursUsed, double teachingHoursLeft) {
        this.userId = userId;
        this.hoursTotal = hoursTotal;
        this.teachingHoursUsed = teachingHoursUsed;
        this.teachingHoursLeft = teachingHoursLeft;
    }

    public TeacherHours() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getHoursTotal() {
        return hoursTotal;
    }

    public void setHoursTotal(double hoursTotal) {
        this.hoursTotal = hoursTotal;
    }

    public double getTeachingHoursUsed() {
        return teachingHoursUsed;
    }

    public void setTeachingHoursUsed(double teachingHoursUsed) {
        this.teachingHoursUsed = teachingHoursUsed;
    }

    public double getTeachingHoursLeft() {
        return teachingHoursLeft;
    }

    public void setTeachingHoursLeft(double teachingHoursLeft) {
        this.teachingHoursLeft = teachingHoursLeft;
    }

    @Override
    public String toString() {
        return "TeacherHours{" +
                "userId=" + userId +
                ", hoursTotal=" + hoursTotal +
                ", teachingHoursUsed=" + teachingHoursUsed +
                ", teachingHoursLeft=" + teachingHoursLeft +
                '}';
    }
}


