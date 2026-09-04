package com.example.diakok;

public class Student {
    private int id;
    private String knev;
    private String vnev;
    private char gender;
    private String schoolclass;
    private String date;
    private String placeOfLiving;
    private int height_cm;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setKnev(String knev) {
        this.knev = knev;
    }

    public String getKnev() {
        return knev;
    }

    public void setVnev(String vnev) {
        this.vnev = vnev;
    }

    public String getVnev() {
        return vnev;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }

    public void setSchoolclass(String schoolclass) {
        this.schoolclass = schoolclass;
    }

    public String getSchoolclass() {
        return schoolclass;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setPlaceOfLiving(String placeOfLiving) {
        this.placeOfLiving = placeOfLiving;
    }

    public String getPlaceOfLiving() {
        return placeOfLiving;
    }

    public void setHeight_cm(int height_cm) {
        this.height_cm = height_cm;
    }

    public int getHeight_cm() {
        return height_cm;
    }
}
