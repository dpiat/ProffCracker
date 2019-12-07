package com.sinhro.mentorapp.Model;

import java.io.Serializable;

public class FullEvent implements Serializable {
    String title;
    String description;
    String time;
    String company;
    double x;
    double y;

    public FullEvent(String title, String description, String time, String company, double x, double y) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.company = company;
        this.x = x;
        this.y = y;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
