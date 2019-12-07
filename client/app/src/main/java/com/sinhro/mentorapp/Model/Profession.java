package com.sinhro.mentorapp.Model;

import java.io.Serializable;

public class Profession implements Serializable {
    public String name;
    public double percent;

    public Profession() {
    }

    public Profession(String name, double percent) {
        this.name = name;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "name='" + name + '\'' +
                ", percent=" + percent +
                '}';
    }
}
