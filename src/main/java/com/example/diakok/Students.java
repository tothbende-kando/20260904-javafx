package com.example.diakok;

public class Students {
    private Student[] students;
    //private int array_length;

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }
/*
    public void setArray_length(int array_length) {
        this.array_length = array_length;
    }

    public int getArray_length() {
        return array_length;
    }
*/
    public Students() {
        students = new Student[200];
    }

    public Students(int length) {
        students = new Student[length];
    }

    public Student[] loadFromFile(String filename) {

    }
}
