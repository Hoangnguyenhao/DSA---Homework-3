import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentSortApp extends Application {

    private TextField nameField, idField, gradeField;
    private TextArea resultArea;
    private List<Student> studentList = new ArrayList<>();
    private ObservableList<String> displayList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information");

        // Create UI components
        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label idLabel = new Label("Student ID:");
        idField = new TextField();

        Label gradeLabel = new Label("Grade:");
        gradeField = new TextField();

        Button addButton = new Button("Add Student");
        Button sortButton = new Button("Sort Students by Grade");
        resultArea = new TextArea();
        resultArea.setEditable(false);

        // Handle "Add Student" button action
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            double grade;
            try {
                grade = Double.parseDouble(gradeField.getText());
            } catch (NumberFormatException ex) {
                // Show an error message if the grade input is not valid
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Invalid grade format");
                alert.setContentText("Please enter a valid number for the grade.");
                alert.showAndWait();
                return;
            }

            // Add student to the list
            Student student = new Student(name, id, grade);
            studentList.add(student);

            // Update the result area
            updateResultArea();
            nameField.clear();
            idField.clear();
            gradeField.clear();
        });

        // Handle "Sort Students by Grade" button action
        sortButton.setOnAction(e -> {
            SelectionSort.sort(studentList);
            updateResultArea();
        });

        // Layout the components in a VBox
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(nameLabel, nameField, idLabel, idField, gradeLabel, gradeField, addButton, sortButton, resultArea);

        // Set up the scene and stage
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Update the result area with the current list of students
    private void updateResultArea() {
        displayList.clear();
        for (Student student : studentList) {
            displayList.add(student.toString());
        }
        resultArea.setText(String.join("\n", displayList));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
