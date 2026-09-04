package com.example.diakok;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

interface StudentFilter {
    boolean is_matched(Student student);
}

public class DiakController {
    private final Students students = new Students(Students.loadFromFile("/com/example/diakok/diakok.csv"));

    @FXML
    private ListView<String> people_list;

    @FXML
    protected void onSaveButtonClick() {

    }

    @FXML
    protected void onEveryoneButtonClick() {
        ObservableList<String> content = FXCollections.observableArrayList(
                filter_people_into_array((Student _) -> true)
        );
        people_list.setItems(content);
    }

    @FXML
    protected void onSandorButtonClick() {
        ObservableList<String> content = FXCollections.observableArrayList(
                filter_people_into_array((Student student) -> student.getKnev().equals("Sándor"))
        );
        people_list.setItems(content);
    }

    @FXML
    protected void onKecskemetButtonClick() {
        ObservableList<String> content = FXCollections.observableArrayList(
                filter_people_into_array((Student student) -> student.getPlaceOfLiving().equals("Kecskemét"))
        );
        people_list.setItems(content);
    }

    @FXML
    protected void onFrom1996Click() {
        ObservableList<String> content = FXCollections.observableArrayList(
                filter_people_into_array((Student student) -> student.getBirthdate().startsWith("1996"))
        );
        people_list.setItems(content);
    }

    @FXML
    protected void onPartOf10AButtonClick() {
        ObservableList<String> content = FXCollections.observableArrayList(
                filter_people_into_array((Student student) -> student.getSchoolclass().equals("10/A"))
        );
        people_list.setItems(content);
    }

    private String[] filter_people_into_array(StudentFilter filter) {
        String[] result = new String[] {};
        Student[] matches = new Student[] {};

        for (Student student : students.getStudents()) {
            if (filter.is_matched(student)) {
                matches = Students.append_student_to_array(student, matches);
            }
        }

        // Lásd: Students.java => append_student_to_array() fölötti komment
        for (Student student : matches) {
            String[] extended = new String[result.length + 1];
            System.arraycopy(result, 0, extended, 0, result.length);
            extended[extended.length - 1] = student.toString();
            result = extended;
        }

        return result;
    }
}