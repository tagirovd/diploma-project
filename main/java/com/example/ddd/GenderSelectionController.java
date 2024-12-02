package com.example.ddd;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import animations.HighlightingBig;
import animations.HighlightingSmall;
import animations.ButtonPressAnimation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GenderSelectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField age_field;

    @FXML
    private Button already_registered_button;

    @FXML
    private Button continue_button;

    @FXML
    private Button genderM_button;

    @FXML
    private Button genderW_button;

    @FXML
    private TextField height_field;

    @FXML
    private TextField weight_field;

    public String gender;
    @FXML
    void AlreadyRegisteredAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "hello-view.fxml");
    }
    @FXML
    void ContinueAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "injury_selection-view.fxml");
    }
    @FXML
    void initialize() {
        HighlightingBig highlighting_continue_button = new HighlightingBig(continue_button);
        HighlightingSmall highlighting_already_registered_button = new HighlightingSmall(already_registered_button);
        ButtonPressAnimation highlighting_genderW_button = new ButtonPressAnimation(genderW_button, genderM_button);
        ButtonPressAnimation highlighting_genderM_button = new ButtonPressAnimation(genderM_button, genderW_button);

        already_registered_button.setOnAction(event -> {
            try {AlreadyRegisteredAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });
        genderW_button.setOnAction(event -> {
            gender = "женщина";
        });
        genderM_button.setOnAction(event -> {
            gender = "мужчина";
        });
        continue_button.setOnAction(event -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("genderRegistrationUser.txt"));
                writer.write(age_field.getText());
                writer.newLine();
                writer.write(height_field.getText());
                writer.newLine();
                writer.write(weight_field.getText());
                writer.newLine();
                writer.write(gender);

                writer.close();
                System.out.println("Variables saved to file successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while saving variables to file.");
                e.printStackTrace();
            }

            try {ContinueAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });
    }
}