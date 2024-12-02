package com.example.ddd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import animations.HighlightingBig;
import animations.HighlightingSmall;
import animations.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button entry_button;

    @FXML
    protected TextField name_field;

    @FXML
    protected PasswordField password_field;

    @FXML
    private Button registration_button;
    @FXML
    private void LoginAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "workout-view.fxml");
    }
    @FXML
    private void RegistrationAction(ActionEvent event) throws Exception{
        Utility.changeToScene (getClass(), event, "registration-view.fxml");
    }
    @FXML
    void initialize() throws ClassNotFoundException {
        HighlightingBig highlighting_entry_button = new HighlightingBig(entry_button);
        HighlightingSmall highlighting_registration_button = new HighlightingSmall(registration_button);

        entry_button.setOnAction(event -> {
            String serverAddress = "localhost";
            int serverPort = 8000;

            try (Socket socket = new Socket(serverAddress, serverPort);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String name = name_field.getText().trim();
                String password = password_field.getText().trim();

                out.println("LOGIN_USER " + password + " " + name);
                String response = in.readLine();
                System.out.println("Server response: " + response);

                if(!name.equals("") && !password.equals(""))

                    try {
                        if(response.equals("true")) {
                            LoginAction(event);
                        } else {
                            Shake nameAnim = new Shake(name_field);
                            Shake loginAnim = new Shake(password_field);
                            nameAnim.playAnim();
                            loginAnim.playAnim();
                            System.out.println("Invalid username or password.");
                        }
                    }catch (Exception e) {throw new RuntimeException(e);}
                else System.out.println("Fill in the blank lines.");
            } catch (IOException e) {e.printStackTrace();}
        });

        registration_button.setOnAction(event -> {
            try {
                RegistrationAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });
    }
}
