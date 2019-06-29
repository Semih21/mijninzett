package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "subject")
public class Subject implements Comparable<Subject>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectId;
    private String subjectName;
    private int estimatedHours;
    private int yearsToExpiryDate;
    private String explanation;

    public Subject() {
        this.yearsToExpiryDate=3;
    }

    public Subject(int subjectId, String subjectName, int estimatedHours, int yearsToExpiryDate, String explanation) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.estimatedHours = estimatedHours;
        this.yearsToExpiryDate = yearsToExpiryDate;
        this.explanation = explanation;

    }

    @Override
    public int compareTo(Subject o) {
        return this.getSubjectId() - o.getSubjectId();
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    @Override
    public String toString() {
        return "******Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", estimatedHours=" + estimatedHours +
                ", yearsToExpiryDate=" + yearsToExpiryDate +
                '}';
    }


}
