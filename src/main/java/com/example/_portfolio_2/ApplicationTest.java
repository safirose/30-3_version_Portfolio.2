
package com.example._portfolio_2;
//Importerer følgende javafx klasser
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class ApplicationTest extends Application {

    // Model-objekt lavet til at hente data og ECTS-point
    private final Model model = new Model();

    // Definerer ComboBox, TextArea og Label til bachelor progam
    private final ComboBox<String> comboBoxProgram = new ComboBox<>();
    private final ComboBox<String> comboBoxCourses = new ComboBox<>();
    private final TextArea textAreaCourses = new TextArea();
    private final Label labelECTSProgram = new Label("0");

    // Definerer ComboBox, TextArea og label til vores første fag
    private final ComboBox<String> comboBoxSubject1 = new ComboBox<>();
    private final ComboBox<String> comboBoxSubjectCourses1 = new ComboBox<>();
    private final TextArea textAreaSubject1 = new TextArea();
    private final Label labelECTSSubject1 = new Label("0");

    // Definerer ComboBox, TextArea og label til vores andet fag
    private final ComboBox<String> comboBoxSubject2 = new ComboBox<>();
    private final ComboBox<String> comboBoxSubjectCourses2 = new ComboBox<>();
    private final TextArea textAreaSubject2 = new TextArea();
    private final Label labelECTSSubject2 = new Label("0");

    // Definerer ComboBox, TextArea og label til vores valgfag
    private final ComboBox<String> comboBoxElectives = new ComboBox<>();
    private final TextArea textAreaElectives = new TextArea();
    private final Label labelECTSElectives = new Label("0");

    // Label til at vise det totale ECTS-point
    private final Label labelECTSTotal = new Label("Total ECTS: 0");

    // Label til de forskellige sektioner, og deres overskrifter
    private final Label label1 = new Label("Bachelor Program");
    private final Label label2 = new Label("Subject Module 1");
    private final Label label3 = new Label("Subject Module 2");
    private final Label label4 = new Label("Electives");

    @Override
    // Start-metoden kaldes automatisk når JavaFX-progammet startes
    public void start(Stage stage) {
        // Her oprettes et GridPane-layout til at lave nogle GUI-komponenter.
        GridPane root = new GridPane();

        // Her initialiserer opsæting for de forskellige sektioner som vi opretter.
        setupProgamSelection();
        setupSubject1Selection();
        setupSubject2Selection();
        setupElectivesSelection();
        setupLayout(root);

       // Opretter scenen med root, layoutet og her bestemmes størrelsen.
        Scene scene = new Scene(root, 800, 400);

        // Vinduets titel
        stage.setTitle("Bachelor Program");

        // Tilføjer scenen til vores vindue, og viser det
        stage.setScene(scene);
        stage.show();
    }

    // Laver bachelor progam sektion
    private void setupProgamSelection() {
        // Tilføjer programmer fra modellen til comboBox program
        comboBoxProgram.getItems().addAll(model.baseProgram());

        // Når brugeren vælger et program
        comboBoxProgram.setOnAction(event -> {
            String selectedProgram = comboBoxProgram.getValue();
            comboBoxCourses.getItems().clear();
            textAreaCourses.clear();
            labelECTSProgram.setText("0");
            if (selectedProgram != null) {

                // Kurser vises som er relateret til det valgte program
                comboBoxCourses.getItems().addAll(model.baseCourse(selectedProgram));

                // Projekter vises som er relateret til det valgte program
                List<String> project = model.baseProject(selectedProgram);
                if (project != null) {
                    comboBoxCourses.getItems().addAll(project);
                }

            }
            updateTotalECTS();
        });

        // Brugerne vælger et kursus som kan vælges ud fra programkurser
        comboBoxCourses.setOnAction(event->{
            String selectedCourse = comboBoxCourses.getValue();
            //Tjekker om kurset allerede er blevet tilføjet
            if (selectedCourse != null && !textAreaCourses.getText().contains(selectedCourse)){
            // Tilføjer kurset til tektsområdet
            textAreaCourses.appendText(selectedCourse + "\n");
            // Opdaterer ECTS-point
            int current = Integer.parseInt(labelECTSProgram.getText());
            labelECTSProgram.setText(String.valueOf(current + model.courseWeight(selectedCourse)));
            updateTotalECTS();
            // Gemmer de valgte kursuser
            model.saveCourse("Safi", selectedCourse);
            }
        });
    }

    // Laver første fagemne sektion
    private void setupSubject1Selection() {
    comboBoxSubject1.getItems().addAll(model.subjectModule());
    comboBoxSubject1.setOnAction(event -> {
    String selectedSubject = comboBoxSubject1.getValue();
    comboBoxSubjectCourses1.getItems().clear();
    textAreaSubject1.clear();
    labelECTSSubject1.setText("0");
    if (selectedSubject != null) {
    comboBoxSubjectCourses1.getItems().addAll(model.subjectCourse(selectedSubject));
    }
    updateTotalECTS();
    });
    comboBoxSubjectCourses1.setOnAction(event -> {
    String selectedCourse = comboBoxSubjectCourses1.getValue();
    if (selectedCourse != null && !textAreaSubject1.getText().contains(selectedCourse)){
        textAreaSubject1.appendText(selectedCourse + "\n");
        int current = Integer.parseInt(labelECTSSubject1.getText());
        labelECTSSubject1.setText(String.valueOf(current+model.courseWeight(selectedCourse)));
        updateTotalECTS();
        model.saveCourse("Safi", selectedCourse);
        }
    });
}
// Opsætter andet fagemne sektion
private void setupSubject2Selection() {
    comboBoxSubject2.getItems().addAll(model.subjectModule());

    comboBoxSubject2.setOnAction(event -> {
        String selectedSubject = comboBoxSubject2.getValue();
        comboBoxSubjectCourses2.getItems().clear();
        textAreaSubject2.clear();
        labelECTSSubject2.setText("0");
        if (selectedSubject != null) {
            comboBoxSubjectCourses2.getItems().addAll(model.subjectCourse(selectedSubject));
        }
        updateTotalECTS();
    });
    comboBoxSubjectCourses2.setOnAction(event -> {
        String selectedCourse = comboBoxSubjectCourses2.getValue();
        if (selectedCourse != null && !textAreaSubject2.getText().contains(selectedCourse)) {
            textAreaSubject2.appendText(selectedCourse + "\n");
            int current = Integer.parseInt(labelECTSSubject2.getText());
            labelECTSSubject2.setText(String.valueOf(current + model.courseWeight(selectedCourse)));
            updateTotalECTS();
            model.saveCourse("Safi", selectedCourse);
        }

    });
}
// Opsætter valgfag sektionen
private void setupElectivesSelection() {
    comboBoxElectives.getItems().addAll("Functional Programming","Scientific Computing");
    comboBoxElectives.setOnAction(event -> {
        String selectedElective = comboBoxElectives.getValue();
        if (selectedElective != null && !textAreaElectives.getText().contains(selectedElective)) {
            textAreaElectives.appendText(selectedElective + "\n");
            int current = Integer.parseInt(labelECTSElectives.getText());
            labelECTSElectives.setText(String.valueOf(current+model.courseWeight(selectedElective)));
            updateTotalECTS();
            model.saveCourse("Safi",selectedElective);
        }
    });
  }

  // Lægger alle elementer i Gridplane Layoutet, og placering kan styres
  private void setupLayout(GridPane root) {
    root.add(label1,0,0);
    root.add(comboBoxProgram, 0, 1);
    root.add(comboBoxCourses,0,2);
    root.add(textAreaCourses,0,3);
    root.add(labelECTSProgram,0,4);

    root.add(label2,1,0);
    root.add(comboBoxSubject1,1,1);
    root.add(comboBoxSubjectCourses1,1,2);
    root.add(textAreaSubject1,1,3);
    root.add(labelECTSSubject1,1,4);

    root.add(label3,2,0);
    root.add(comboBoxSubject2,2,1);
    root.add(comboBoxSubjectCourses2,2,2);
    root.add(textAreaSubject2,2,3);
    root.add(labelECTSSubject2,2,4);

    root.add(label4,3,0);
    root.add(comboBoxElectives,3,2);
    root.add(textAreaElectives,3,3);
    root.add(labelECTSElectives,3,4);
    root.add(labelECTSTotal,3,5);
    }
    // Opdatere den samelde ECTS-point og tilføje det i label
    private void updateTotalECTS() {
        int total = Integer.parseInt(labelECTSProgram.getText())+
                Integer.parseInt(labelECTSSubject1.getText())+
                Integer.parseInt(labelECTSSubject2.getText())+
                Integer.parseInt(labelECTSElectives.getText());
        labelECTSTotal.setText("Total ECTS: " + total);
    }
    // Main-metoden som starter applikationen
    public static void main(String[] args) {
        launch(args);
    }
}
