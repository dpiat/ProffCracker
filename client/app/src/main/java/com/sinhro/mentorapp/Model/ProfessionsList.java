package com.sinhro.mentorapp.Model;

import java.io.Serializable;
import java.util.List;

public class ProfessionsList implements Serializable {
    List<Profession> professionList;

    public ProfessionsList(List<Profession> professionList) {
        this.professionList = professionList;
    }

    public List<Profession> getProfessionList() {
        return professionList;
    }

    public void setProfessionList(List<Profession> professionList) {
        this.professionList = professionList;
    }


}
