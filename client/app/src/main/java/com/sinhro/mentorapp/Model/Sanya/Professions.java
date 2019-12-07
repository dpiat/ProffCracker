package com.sinhro.mentorapp.Model.Sanya;

import com.sinhro.mentorapp.Model.Profession;

import java.util.Map;

public class Professions {
    Map<Integer, Profession> profs;

    public Professions(Map<Integer, Profession> profs) {
        this.profs = profs;
    }

    @Override
    public String toString() {
        return "Professions{" +
                "profs=" + profs.toString() +
                '}';
    }

    public Professions(){}

    public Map<Integer, Profession> getProfs() {
        return profs;
    }

    public void setProfs(Map<Integer, Profession> profs) {
        this.profs = profs;
    }
}
