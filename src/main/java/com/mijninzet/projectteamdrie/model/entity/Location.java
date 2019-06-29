package com.mijninzet.projectteamdrie.model.entity;



import com.mijninzet.projectteamdrie.model.entity.user.User;

import java.util.InputMismatchException;


public class Location implements Comparable<Location> {
    private String description;
    private int courseId;
    private User coordinator;

    
    public Location(String description, User coordinator) throws InputMismatchException{
        this.description = description;
        this.coordinator= coordinator;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Location o) {
        return this.description.compareTo(o.description);
    }
    
    public User getCoordinator() {
        return coordinator;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoordinator(User coordinator) {
        this.coordinator = coordinator;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Location){
            return this.getCourseId()==((Location) obj).getCourseId()&&
                    this.getDescription()==((Location) obj).getDescription()&&
                    this.getCoordinator()==((Location) obj).getCoordinator();
        }
        return false;
    }

    @Override
    public String toString () {
        return String.format("%s, %d, %s",getDescription(), getCourseId(), getCoordinator().getLastName()); //getCoordinator().getUserName();
    }
}
