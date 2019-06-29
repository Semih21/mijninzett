package com.mijninzet.projectteamdrie.model.entity;
import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import javax.persistence.*;

@Entity
public class StaffAvailability {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String maandagOchtend;
    private String maandagMiddag;
    private String maandagAvond;
    private String dinsdagOchtend;
    private String dinsdagMiddag;
    private String dinsdagAvond;
    private String woensdagOchtend;
    private String woensdagMiddag;
    private String woensdagAvond;
    private String donderdagOchtend;
    private String donderdagMiddag;
    private String donderdagAvond;
    private String vrijdagOchtend;
    private String vrijdagMiddag;
    private String vrijdagAvond;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="cohort")
    private Cohort cohort;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    private User user;

    public StaffAvailability() {
        final int userId = UserSingleton.getInstance().getId();
        this.user = new User(userId);
    }

    public StaffAvailability(String maandagOchtend, String maandagMiddag, String maandagAvond, String dinsdagOchtend,
                             String dinsdagMiddag, String dinsdagAvond, String woensdagOchtend, String woensdagMiddag,
                             String woensdagAvond, String donderdagOchtend, String donderdagMiddag, String donderdagAvond,
                             String vrijdagOchtend, String vrijdagMiddag, String vrijdagAvond, Cohort cohort, User user) {
        this.maandagOchtend = maandagOchtend;
        this.maandagMiddag = maandagMiddag;
        this.maandagAvond = maandagAvond;
        this.dinsdagOchtend = dinsdagOchtend;
        this.dinsdagMiddag = dinsdagMiddag;
        this.dinsdagAvond = dinsdagAvond;
        this.woensdagOchtend = woensdagOchtend;
        this.woensdagMiddag = woensdagMiddag;
        this.woensdagAvond = woensdagAvond;
        this.donderdagOchtend = donderdagOchtend;
        this.donderdagMiddag = donderdagMiddag;
        this.donderdagAvond = donderdagAvond;
        this.vrijdagOchtend = vrijdagOchtend;
        this.vrijdagMiddag = vrijdagMiddag;
        this.vrijdagAvond = vrijdagAvond;
        this.cohort = cohort;
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

    public String getMaandagAvond() {
        return maandagAvond;
    }

    public void setMaandagAvond(String maandagAvond) {
        this.maandagAvond = maandagAvond;
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

    public String getDinsdagAvond() {
        return dinsdagAvond;
    }

    public void setDinsdagAvond(String dinsdagAvond) {
        this.dinsdagAvond = dinsdagAvond;
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

    public String getWoensdagAvond() {
        return woensdagAvond;
    }

    public void setWoensdagAvond(String woensdagAvond) {
        this.woensdagAvond = woensdagAvond;
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

    public String getDonderdagAvond() {
        return donderdagAvond;
    }

    public void setDonderdagAvond(String donderdagAvond) {
        this.donderdagAvond = donderdagAvond;
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

    public String getVrijdagAvond() {
        return vrijdagAvond;
    }

    public void setVrijdagAvond(String vrijdagAvond) {
        this.vrijdagAvond = vrijdagAvond;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StaffAvailability{" +
                "id=" + id +
                ", maandagOchtend='" + maandagOchtend + '\'' +
                ", maandagMiddag='" + maandagMiddag + '\'' +
                ", maandagAvond='" + maandagAvond + '\'' +
                ", dinsdagOchtend='" + dinsdagOchtend + '\'' +
                ", dinsdagMiddag='" + dinsdagMiddag + '\'' +
                ", dinsdagAvond='" + dinsdagAvond + '\'' +
                ", woensdagOchtend='" + woensdagOchtend + '\'' +
                ", woensdagMiddag='" + woensdagMiddag + '\'' +
                ", woensdagAvond='" + woensdagAvond + '\'' +
                ", donderdagOchtend='" + donderdagOchtend + '\'' +
                ", donderdagMiddag='" + donderdagMiddag + '\'' +
                ", donderdagAvond='" + donderdagAvond + '\'' +
                ", vrijdagOchtend='" + vrijdagOchtend + '\'' +
                ", vrijdagMiddag='" + vrijdagMiddag + '\'' +
                ", vrijdagAvond='" + vrijdagAvond + '\'' +
                ", cohort=" + cohort +
                ", user=" + user +
                '}';
    }
}
