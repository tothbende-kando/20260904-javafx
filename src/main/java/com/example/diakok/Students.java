package com.example.diakok;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.util.Objects;


public class Students {
    private Student[] students;

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public Students() {
        students = new Student[200];
    }

    public Students(Student[] students) {
        this.students = students;
    }


    public static Student[] loadFromFile(String filename) {
        return process_student_file(filename);
    }


    private static Student[] process_student_file(String filename) {
        Student[] students = new Student[] {};

        try {
            // I love java
            InputStreamReader file = new InputStreamReader(Objects.requireNonNull(Students.class.getResourceAsStream(filename)));
            BufferedReader student_file = new BufferedReader(file);

            String line = student_file.readLine();
            if (line == null) {
                throw new EOFException(String.format("The file \"%s\" is empty.", filename));
            }

            while (line != null) {
                students = append_student_to_array(extract_student_data_from_cvs(line), students);
                line = student_file.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }


    private static Student extract_student_data_from_cvs(String student_cvs_data) {
        String[] fields = student_cvs_data.split(";");
        return new Student(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3].charAt(0),
                fields[4],
                fields[5],
                fields[6],
                Integer.parseInt(fields[7]));
    }


    // Nem szép itt ez a public de kellett DiakControllerbe és biztos nem írom le ugyanazt a kódot máshova
    // Tudom tudom, Utils.java => append_to_array() generic típusokkal, de lusta vagyok
    public static Student[] append_student_to_array(Student new_student, Student[] base_array) {
        Student[] extended = new Student[base_array.length + 1];

        // IntelliJ auto-suggestion, nem is olyan rossz ötlet (nyilván csak az*után*, hogy megírtam a for-t)
        System.arraycopy(base_array, 0, extended, 0, base_array.length);

        extended[extended.length - 1] = new_student;
        return extended;
    }
}
