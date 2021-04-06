package com.example.project;

public class Example_doctor {
    private int ImageResource;
    private String Name;
    private String Specialization;
    private String Experience;
    private String Location;
    private String Hospital;

    public Example_doctor(){}
    public Example_doctor(int ImageResource,String Name,String Special,String Years,String Location,String Hospital){
        this.ImageResource = ImageResource;
        this.Name = Name;
        this.Specialization = Special;
        this.Experience = Years;
        this.Location = Location;
        this.Hospital = Hospital;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public String getName() {
        return Name;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public String getExperience() {
        return Experience;
    }

    public String getLocation() {
        return Location;
    }

    public String getHospital() {
        return Hospital;
    }
}
