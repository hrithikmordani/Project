package com.example.project;

public class Example_disease {
    private String Cases;
    private String Common;
    private String Name;
    private String Tag1;
    private String Tag2;
    private String Tag3;
    private String Tag4;
    private String Tag5;
    private String Tag6;

    public Example_disease(){}
    public Example_disease(String Cases,String Common,String Name,String Tag1,String Tag2,String Tag3,String Tag4,
                           String Tag5,String Tag6){
        this.Cases=Cases;
        this.Common=Common;
        this.Name=Name;
        this.Tag1=Tag1;
        this.Tag2=Tag2;
        this.Tag3=Tag3;
        this.Tag4=Tag4;
        this.Tag5=Tag5;
        this.Tag6=Tag6;
    }

    public String getCases() {
        return Cases;
    }

    public String getCommon() {
        return Common;
    }

    public String getName() {
        return Name;
    }

    public String getTag1() {
        return Tag1;
    }

    public String getTag2() {
        return Tag2;
    }

    public String getTag3() {
        return Tag3;
    }

    public String getTag4() {
        return Tag4;
    }

    public String getTag5() {
        return Tag5;
    }

    public String getTag6() {
        return Tag6;
    }
}

