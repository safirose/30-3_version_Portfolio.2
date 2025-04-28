
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

    private final Model model = new Model();

    private final ComboBox<String> comboBoxProgram = new ComboBox<>();
    private final ComboBox<String> comboBoxCourses = new ComboBox<>();
    private final TextArea textAreaCourses = new TextArea();
    private final Label labelECTSProgram = new Label("0");

    private final ComboBox<String> comboBoxSubject1 = new ComboBox<>();
    private final ComboBox<String> comboBoxSubjectCourses1 = new ComboBox<>();
    private final TextArea textAreaSubject1 = new TextArea();
    private final Label labelECTSSubject1 = new Label("0");

    private final ComboBox<String> comboBoxSubject2 = new ComboBox<>();
    private final ComboBox<String> comboBoxSubjectCourses2 = new ComboBox<>();
    private final TextArea textAreaSubject2 = new TextArea();
    private final Label labelECTSSubject2 = new Label("0");

    private final ComboBox<String> comboBoxElectives = new ComboBox<>();
    private final TextArea textAreaElectives = new TextArea();
    private final Label labelECTSElectives = new Label("0");

    private final Label labelECTSTotal = new Label("Total ECTS: 0");

    private final Label label1 = new Label("Bachelor Program");
    private final Label label2 = new Label("Subject Module 1");
    private final Label label3 = new Label("Subject Module 2");
    private final Label label4 = new Label("Electives");

    /*// Labels til ECTS
    Label labelECTSProgram = new Label("0");
    Label labelECTSSubject1 = new Label("0");
    Label labelECTSSubject2 = new Label("0");
    Label labelECTSElectives = new Label("0");
    Label labelECTSTotal = new Label("total 0");

    Model model = new Model();*/

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        setupProgamSelection();
        setupSubject1Selection();
        setupSubject2Selection();
        setupElectivesSelection();
        setupLayout(root);

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Bachelor Program");
        stage.setScene(scene);
        stage.show();
    }
    private void setupProgamSelection() {
        comboBoxProgram.getItems().addAll(model.baseProgram());

        comboBoxProgram.setOnAction(event -> {
            String selectedProgram = comboBoxProgram.getValue();
            comboBoxCourses.getItems().clear();
            textAreaCourses.clear();
            labelECTSProgram.setText("0");
            if (selectedProgram != null) {
                comboBoxCourses.getItems().addAll(model.baseCourse(selectedProgram));

            }
            updateTotalECTS();
        });
        comboBoxCourses.setOnAction(event->{
            String selectedCourse = comboBoxCourses.getValue();
            if (selectedCourse != null && !textAreaCourses.getText().contains(selectedCourse)){
            textAreaCourses.appendText(selectedCourse + "\n");
            int current = Integer.parseInt(labelECTSProgram.getText());
            labelECTSProgram.setText(String.valueOf(current + model.courseWeight(selectedCourse)));
            updateTotalECTS();
            model.saveCourse("Safi", selectedCourse);
            }
        });
    }

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
    private void updateTotalECTS() {
        int total = Integer.parseInt(labelECTSProgram.getText())+
                Integer.parseInt(labelECTSSubject1.getText())+
                Integer.parseInt(labelECTSSubject2.getText())+
                Integer.parseInt(labelECTSElectives.getText());
        labelECTSTotal.setText("Total ECTS: " + total);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
    /*


        // GUI Labels/Overskrifter
        Label label1 = new Label("Bachelor Program");
        Label label2 = new Label("Subject 1");
        Label label3 = new Label("Subject 2");
        Label label4 = new Label("Electives");

        // Comboox til bachProgram
        ComboBox<String> comboBoxProgram = new ComboBox<>();
        //henter alle basiskurser fra vores 'Model' klasse, og tilføjer dem til combobox
        comboBoxProgram.getItems().addAll(model.baseProgram());
        //Combobox til kurser fra det valgte bachprogram
        ComboBox<String> comboBoxCourses = new ComboBox<>();
        //Opretter et textarea til de valgte kurser, vi sætter 'editable' til false så %skrive i area
        TextArea textAreaCourses = new TextArea();
        textAreaCourses.setEditable(false);

        //Action event til vores bachprogram kollonne
        //Når et program vælges--> opdateres tilknyttet kurser
        comboBoxProgram.setOnAction(event -> {
            String selectedProgram = comboBoxProgram.getValue();
            //ryd gamle kurser
            comboBoxCourses.getItems().clear();
            textAreaCourses.clear();
            labelECTSProgram.setText("0");
            if (selectedProgram != null) {
                comboBoxCourses.getItems().addAll(model.baseCourse(selectedProgram));
                List<String> projects = model.baseProject(selectedProgram);
                if (projects != null) {
                    comboBoxCourses.getItems().addAll(projects);
                }
            }
            //opdater ECTS-værdi
            updateTotalECTS();
        });
        //
        comboBoxCourses.setOnAction(event -> {
            String selectedCourse = comboBoxCourses.getValue();
            if (selectedCourse != null && !textAreaCourses.getText().contains(selectedCourse)) {
                textAreaCourses.appendText(selectedCourse + "\n");
                int current = Integer.parseInt(labelECTSProgram.getText());
                labelECTSProgram.setText(String.valueOf(current + model.courseWeight(selectedCourse)));
                updateTotalECTS();
                model.saveCourse("Safi", selectedCourse); // Gem til database
            }
        });


        // FAGMODULER
        ComboBox<String> comboBoxSubject1 = new ComboBox<>();
        ComboBox<String> comboBoxSubject2 = new ComboBox<>();
        TextArea textAreaSubject1 = new TextArea();
        TextArea textAreaSubject2 = new TextArea();
        textAreaSubject1.setEditable(false);
        textAreaSubject2.setEditable(false);

        comboBoxSubject1.getItems().addAll(model.subjectModule());
        comboBoxSubject2.getItems().addAll(model.subjectModule());

        ComboBox<String> comboBoxSubjectCourses1 = new ComboBox<>();
        ComboBox<String> comboBoxSubjectCourses2 = new ComboBox<>();

        comboBoxSubject1.setOnAction(event -> {
            String selectedSubject = comboBoxSubject1.getValue();
            comboBoxSubjectCourses1.getItems().clear();
            textAreaSubject1.clear();
            labelECTSSubject1.setText("0");
            if (selectedSubject != null) {
                comboBoxSubjectCourses1.getItems().addAll(model.subjectCourse(selectedSubject));
                comboBoxSubjectCourses1.getItems().addAll(model.subjectProject(selectedSubject));
            }
            updateTotalECTS();
        });

        comboBoxSubject2.setOnAction(event -> {
            String selectedSubject = comboBoxSubject2.getValue();
            comboBoxSubjectCourses2.getItems().clear();
            textAreaSubject2.clear();
            labelECTSSubject2.setText("0");
            if (selectedSubject != null) {
                comboBoxSubjectCourses2.getItems().addAll(model.subjectCourse(selectedSubject));
                comboBoxSubjectCourses2.getItems().addAll(model.subjectProject(selectedSubject));
            }
            updateTotalECTS();
        });

        comboBoxSubjectCourses1.setOnAction(event -> {
            String selectedCourse = comboBoxSubjectCourses1.getValue();
            if (selectedCourse != null && !textAreaSubject1.getText().contains(selectedCourse)) {
                textAreaSubject1.appendText(selectedCourse + "\n");
                int current = Integer.parseInt(labelECTSSubject1.getText());
                labelECTSSubject1.setText(String.valueOf(current + model.courseWeight(selectedCourse)));
                updateTotalECTS();
                model.saveCourse("Safi", selectedCourse);
                updateTotalECTS();
                String subjectProjects = model.subjectProject(selectedCourse);
                if (subjectProjects != null) {
                    comboBoxSubjectCourses1.getItems().addAll(subjectProjects);

            }
        }
                });

        comboBoxSubjectCourses2.setOnAction(event -> {
            String selectedCourse = comboBoxSubjectCourses2.getValue();
            if (selectedCourse != null && !textAreaSubject2.getText().contains(selectedCourse)) {
                textAreaSubject2.appendText(selectedCourse + "\n");
                int current = Integer.parseInt(labelECTSSubject2.getText());
                labelECTSSubject2.setText(String.valueOf(current + model.courseWeight(selectedCourse)));
                updateTotalECTS();
                model.saveCourse("Safi", selectedCourse);

                updateTotalECTS();
            }
        });

        // Electives
        ComboBox<String> comboBoxElectives = new ComboBox<>();
        comboBoxElectives.getItems().addAll("Functional Programming", "Scientific Computing");
        TextArea textAreaElective = new TextArea();
        textAreaElective.setEditable(false);

        comboBoxElectives.setOnAction(event -> {
            String selectedElective = comboBoxElectives.getValue();
            if (selectedElective != null && !textAreaElective.getText().contains(selectedElective)) {
                textAreaElective.appendText(selectedElective + "\n");
                int current = Integer.parseInt(labelECTSElectives.getText());
                labelECTSElectives.setText(String.valueOf(current + model.courseWeight(selectedElective)));
                updateTotalECTS();
                model.saveCourse("Safi", selectedElective);
                updateTotalECTS();
            }
        });

        // Layout
        GridPane root = new GridPane();

        root.add(label1, 0, 0);
        root.add(comboBoxProgram, 0, 1);
        root.add(comboBoxCourses, 0, 2);
        root.add(textAreaCourses, 0, 3);
        root.add(labelECTSProgram, 0, 4);

        root.add(label2, 1, 0);
        root.add(comboBoxSubject1, 1, 1);
        root.add(comboBoxSubjectCourses1, 1, 2);
        root.add(textAreaSubject1, 1, 3);
        root.add(labelECTSSubject1, 1, 4);

        root.add(label3, 2, 0);
        root.add(comboBoxSubject2, 2, 1);
        root.add(comboBoxSubjectCourses2, 2, 2);
        root.add(textAreaSubject2, 2, 3);
        root.add(labelECTSSubject2, 2, 4);

        root.add(label4, 3, 0);
        root.add(comboBoxElectives, 3, 2);
        root.add(textAreaElective, 3, 3);
        root.add(labelECTSElectives, 3, 4);
        root.add(labelECTSTotal, 3, 5);

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Bachelor Programme");
        stage.setScene(scene);
        stage.show();
    }
//metode til at opdatere ECTS-point
    void updateTotalECTS() {
        int total = Integer.parseInt(labelECTSProgram.getText()) +
                Integer.parseInt(labelECTSSubject1.getText()) +
                Integer.parseInt(labelECTSSubject2.getText()) +
                Integer.parseInt(labelECTSElectives.getText());
        labelECTSTotal.setText("total " + total);
    }

    public static void main(String[] args) {
        launch();
    }
}*/
//Note: lav programmet metodeorienteret
//Note: Inkluder en metode til projekter, igen tjek i database!
