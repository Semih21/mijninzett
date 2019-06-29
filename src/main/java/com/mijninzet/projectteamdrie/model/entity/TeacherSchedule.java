package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;


@Entity
public class TeacherSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String maandagOchtend;
    private String maandagMiddag;
    private String dinsdagOchtend;
    private String dinsdagMiddag;
    private String woensdagOchtend;
    private String woensdagMiddag;
    private String donderdagOchtend;
    private String donderdagMiddag;
    private String vrijdagOchtend;
    private String vrijdagMiddag;
    private int weeknr;
    private int cohortId;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    public TeacherSchedule() {
        final int userId = UserSingleton.getInstance().getId();
        this.user = new User(userId);
    }

    public TeacherSchedule(String maandagOchtend, String maandagMiddag, String dinsdagOchtend,
                           String dinsdagMiddag,  String woensdagOchtend, String woensdagMiddag,
                            String donderdagOchtend, String donderdagMiddag,
                           String vrijdagOchtend, String vrijdagMiddag,  int weeknr, int cohortId , User user) {
        this.maandagOchtend = maandagOchtend;
        this.maandagMiddag = maandagMiddag;
        this.dinsdagOchtend = dinsdagOchtend;
        this.dinsdagMiddag = dinsdagMiddag;
        this.woensdagOchtend = woensdagOchtend;
        this.woensdagMiddag = woensdagMiddag;
        this.donderdagOchtend = donderdagOchtend;
        this.donderdagMiddag = donderdagMiddag;
        this.vrijdagOchtend = vrijdagOchtend;
        this.vrijdagMiddag = vrijdagMiddag;
        this.cohortId = cohortId;
        this.weeknr = weeknr;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaandagOchtend() {
        return maandagOchtend;
    }

    public void setMaandagOchtend(String maandagOchtend) {
        this.maandagOchtend = maandagOchtend;
    }

    public String getMaandagMiddag() {
        return maandagMiddag;
    }

    public void setMaandagMiddag(String maandagMiddag) {
        this.maandagMiddag = maandagMiddag;
    }

    public String getDinsdagOchtend() {
        return dinsdagOchtend;
    }

    public void setDinsdagOchtend(String dinsdagOchtend) {
        this.dinsdagOchtend = dinsdagOchtend;
    }

    public String getDinsdagMiddag() {
        return dinsdagMiddag;
    }

    public void setDinsdagMiddag(String dinsdagMiddag) {
        this.dinsdagMiddag = dinsdagMiddag;
    }

    public String getWoensdagOchtend() {
        return woensdagOchtend;
    }

    public void setWoensdagOchtend(String woensdagOchtend) {
        this.woensdagOchtend = woensdagOchtend;
    }

    public String getWoensdagMiddag() {
        return woensdagMiddag;
    }

    public void setWoensdagMiddag(String woensdagMiddag) {
        this.woensdagMiddag = woensdagMiddag;
    }

    public String getDonderdagOchtend() {
        return donderdagOchtend;
    }

    public void setDonderdagOchtend(String donderdagOchtend) {
        this.donderdagOchtend = donderdagOchtend;
    }

    public String getDonderdagMiddag() {
        return donderdagMiddag;
    }

    public void setDonderdagMiddag(String donderdagMiddag) {
        this.donderdagMiddag = donderdagMiddag;
    }

    public String getVrijdagOchtend() {
        return vrijdagOchtend;
    }

    public void setVrijdagOchtend(String vrijdagOchtend) {
        this.vrijdagOchtend = vrijdagOchtend;
    }

    public String getVrijdagMiddag() {
        return vrijdagMiddag;
    }

    public void setVrijdagMiddag(String vrijdagMiddag) {
        this.vrijdagMiddag = vrijdagMiddag;
    }

    public int getCohortId() {
        return cohortId;
    }

    public void setCohortId(int cohortId) {
        this.cohortId = cohortId;
    }

    public int getWeeknr() {
        return weeknr;
    }

    public void setWeeknr(int weeknr) {
        this.weeknr = weeknr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TeacherSchedule{" +
                "id=" + id +
                ", maandagOchtend='" + maandagOchtend + '\'' +
                ", maandagMiddag='" + maandagMiddag + '\'' +
                ", dinsdagOchtend='" + dinsdagOchtend + '\'' +
                ", dinsdagMiddag='" + dinsdagMiddag + '\'' +
                ", woensdagOchtend='" + woensdagOchtend + '\'' +
                ", woensdagMiddag='" + woensdagMiddag + '\'' +
                ", donderdagOchtend='" + donderdagOchtend + '\'' +
                ", donderdagMiddag='" + donderdagMiddag + '\'' +
                ", vrijdagOchtend='" + vrijdagOchtend + '\'' +
                ", vrijdagMiddag='" + vrijdagMiddag + '\'' +
                ", cohortId=" + cohortId +'\''+
                ", weeknr =" + weeknr + '\''+
                ", user=" + user +
                '}';
    }
}


