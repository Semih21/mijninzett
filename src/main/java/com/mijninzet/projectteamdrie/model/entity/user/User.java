package com.mijninzet.projectteamdrie.model.entity.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull(message="First name is compulsory")
    @Column(name = "first_name")
    private String name;

    @NotNull(message="Last name is compulsory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message="Email is compulsory")
    @Email(message = "Email is invalid")
    @Column(name = "email")
    private String email;

    @NotNull(message="Password is compulsory")
    @Length(min=5, message="Password should be at least 5 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name="fte")
    private Double fte = 1.0;


    private boolean isEnabled;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> rolesOfUser;

    // Brahim Code: current user ID wordt hier bepaalt ingesteld;
    // dit is geen onderdeel vd Bean! en mag niet in een constructor
    public static int CurrentUserId;
    public static int getCurrentUserId() {
        return CurrentUserId;    }
    public static void setCurrentUserId(int currentUserId) {
        CurrentUserId = currentUserId;
    }
    // Brahim Code end ------

    public User() { }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRolesOfUser() {
        return rolesOfUser;
    }

    public void setRolesOfUser(List<Role> rolesOfUser) {
        this.rolesOfUser = rolesOfUser;
    }

    public Double getFte() {
        return fte;
    }

    public void setFte(Double fte) {
        this.fte = fte;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return  name + " " + lastName ;
    }
}







//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity(name = "user")
//@DiscriminatorColumn(name = "role",
//        discriminatorType = DiscriminatorType.STRING)
//public abstract class User implements Comparable<User>{
//    @Id
//    private int id;
//    private String username;
//    private String password;
//    //@ozgur
//    // dit variable is verwijderd omdat in de role van elke user wordt gegeven via discriminator value zoals Teacher.
////    @Enumerated(EnumType.STRING)
////    private Role role;
//
//
//
//    private static final String DEFAULT = "unknown";
//    public User() {
//        this(DEFAULT, DEFAULT);
//    }
//
//    public User(int id) {
//        this.id = id;
//    }
//
//    public User(String username, String password) {
//        this(username, password, null);
//    }
//
//    //all args
//    public User(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
////        this.role = role;
//    }
//
//    @Override
//    public int compareTo(User o) {
//        return this.username.compareTo(o.username);
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
////    public Role getRoleName() {
////        return role;
////    }
////
////        public void setRoleName(Role role) {
////        this.role = role;
////    }
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int userID) {
//        this.id = userID;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//    public String toString() {
//
//        return String.format("User %s with ID: %d", username, id);
//    }
//
//}