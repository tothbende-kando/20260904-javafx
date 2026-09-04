package com.example.diakok;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.FileWriter;

interface StudentFilter {
    boolean is_matched(Student student);
}

public class DiakController {
    private final Students students = new Students(Students.loadFromFile("/com/example/diakok/diakok.csv"));
    private String[] current_content = new String[0];

    @FXML
    private ListView<String> people_list;

    @FXML
    protected void onSaveButtonClick() {
        try {
/* print cwd
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current absolute path is: " + s);
*/
            FileWriter writer = new FileWriter("adatok.txt");
            writer.write("");

            for (String line : current_content) {
                writer.append(line);
                writer.append("\n");
            }

            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onEveryoneButtonClick() {
        current_content = query_students_by_filter((Student _) -> true);
        people_list.setItems(FXCollections.observableArrayList(current_content));
    }

    @FXML
    protected void onSandorButtonClick() {
        current_content = query_students_by_filter((Student student) -> student.getKnev().equals("Sándor"));
        people_list.setItems(FXCollections.observableArrayList(current_content));
    }

    @FXML
    protected void onKecskemetButtonClick() {
        current_content = query_students_by_filter((Student student) -> student.getPlaceOfLiving().equals("Kecskemét"));
        people_list.setItems(FXCollections.observableArrayList(current_content));
    }

    @FXML
    protected void onFrom1996Click() {
        current_content = query_students_by_filter((Student student) -> student.getBirthdate().startsWith("1996"));
        people_list.setItems(FXCollections.observableArrayList(current_content));
    }

    @FXML
    protected void onPartOf10AButtonClick() {
        current_content = query_students_by_filter((Student student) -> student.getSchoolclass().equals("10/A"));
        people_list.setItems(FXCollections.observableArrayList(current_content));
    }

    private String[] query_students_by_filter(StudentFilter filter) {
        Student[] matches = new Student[] {};
        current_content = new String[] {};

        for (Student student : students.getStudents()) {
            if (filter.is_matched(student)) {
                matches = Students.append_student_to_array(student, matches);
            }
        }

        // Lásd: Students.java => append_student_to_array() fölötti komment
        for (Student student : matches) {
            String[] extended = new String[current_content.length + 1];
            System.arraycopy(current_content, 0, extended, 0, current_content.length);
            extended[extended.length - 1] = student.toString();
            current_content = extended;
        }

        return current_content;
    }
}