package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Entity
public class CohortSchedule {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String day;
   private String daypart;

   private LocalDate date;
    private int weeknr;

   @ManyToOne
   User user;

   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
   Subject subject;

   private String classRoom;

   @ManyToOne
   Cohort cohort;

   public CohortSchedule() {
      super();
   }

   public CohortSchedule(int id, LocalDate date, String day, String daypart, User user, Subject subject, String classRoom, Cohort cohort, int weeknr) {
      this.id = id;
      this.classRoom = classRoom;
      this.day = day;
      this.daypart = daypart;
      this.date = date;
      this.user = user;
      this.subject = subject;
      this.cohort = cohort;
      this.weeknr=weeknr;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public String getDay() {
      return day;
   }

   public void setDay(String day) {
      this.day = day;
   }

   public String getDaypart() {
      return daypart;
   }

   public void setDaypart(String daypart) {
      this.daypart = daypart;
   }

   public String getClassRoom() {
      return classRoom;
   }

   public void setClassRoom(String classRoom) {
      this.classRoom = classRoom;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Subject getSubject() {
      return subject;
   }

   public void setSubject(Subject subject) {
      this.subject = subject;
   }

   public Cohort getCohort() {
      return cohort;
   }

   public void setCohort(Cohort cohort) {
      this.cohort = cohort;
   }

   public int getWeeknr() {
      return weeknr;
   }

   public void setWeeknr(int weeknr) {
      this.weeknr = weeknr;
   }

   @Override
   public String toString() {
      return "CohortSchedule{" +
              "id=" + id +
              ", day='" + day + '\'' +
              ", daypart='" + daypart + '\'' +
              ", date=" + date +
              ", user=" + user +
              ", subject=" + subject +
              ", classRoom='" + classRoom + '\'' +
              ", cohort=" + cohort +
              '}';
   }
}
