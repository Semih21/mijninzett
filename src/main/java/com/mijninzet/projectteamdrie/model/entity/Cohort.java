package com.mijninzet.projectteamdrie.model.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Cohort implements Comparable<Cohort> {

    @Id
    private int cohortId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Cohort() {
    }

    public Cohort(int cohortId, LocalDate beginDate, LocalDate endDate) {
        this.cohortId = cohortId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getCohortId() {
        return cohortId;
    }

    public void setCohortId(int cohortId) {
        this.cohortId = cohortId;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return " Cohort " +
                "  " + cohortId +
                " | vanaf " + beginDate +
                " tot " + endDate;
    }

    @Override
    public int compareTo(Cohort o) {
        return this.cohortId - o.cohortId;
    }
}
