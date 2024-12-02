package com.example.ddd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SuccessfulRegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button entry_button;
    @FXML
    private void ContinueAction(ActionEvent event) throws Exception{
        Utility.changeToScene (getClass(), event, "hello-view.fxml");
    }
    @FXML
    void initialize() {
        entry_button.setOnAction(event -> {
            try {ContinueAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });
    }

}
