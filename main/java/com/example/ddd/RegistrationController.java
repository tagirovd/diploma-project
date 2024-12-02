package com.example.ddd;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import animations.HighlightingBig;
import animations.HighlightingSmall;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button already_registered_button;

    @FXML
    private Button continue_button;

    @FXML
    private TextField email_field;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private void ContinueAction(ActionEvent event) throws Exception{
        Utility.changeToScene (getClass(), event, "gender_selection-view.fxml");
    }
    @FXML
    private void AlreadyRegisteredAction(ActionEvent event) throws Exception{
        Utility.changeToScene (getClass(), event, "hello-view.fxml");
    }

    @FXML
    void initialize() {
        HighlightingBig highlighting_continue_button = new HighlightingBig(continue_button);
        HighlightingSmall highlighting_already_registered_button = new HighlightingSmall(already_registered_button);

        continue_button.setOnAction(event -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("registrationUser.txt"));
                writer.write(password_field.getText());
                writer.newLine();
                writer.write(name_field.getText());
                writer.newLine();
                writer.write(email_field.getText());

                writer.close();
                System.out.println("Variables saved to file successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while saving variables to file.");
                e.printStackTrace();
            }
            try {ContinueAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });

        already_registered_button.setOnAction(event -> {
            try {AlreadyRegisteredAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });
    }
}
