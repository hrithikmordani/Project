package com.example.project;

import java.sql.Time;

public class Schedule {
    private String Patient_name;
    private String Time_slot;
    public Schedule(){}
    public Schedule(String Patient_name,String Time_slot){
        this.Patient_name = Patient_name;
        this.Time_slot= Time_slot;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public String getTime_slot() {
        return Time_slot;
    }
}
