package com.example.ddd;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LevelSelectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField r_bench_press_field;

    @FXML
    private Button continue_button;

    @FXML
    private TextField r_deadlift_field;

    @FXML
    private Button already_registered_button;

    @FXML
    private TextField r_squats_field;

    @FXML
    private TextField w_bench_press_field;

    @FXML
    private TextField w_deadlift_field;

    @FXML
    private TextField w_squats_field;

    @FXML
    void ContinueAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "successful_registration-view.fxml");
    }

    @FXML
    void AlreadyRegisteredAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "hello-view.fxml");
    }

    @FXML
    void initialize() {
        continue_button.setOnAction(event -> {
            String serverAddress = "localhost";
            int serverPort = 8000;

            try (Socket socket = new Socket(serverAddress, serverPort);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                try (BufferedReader br = new BufferedReader(new FileReader("registrationUser.txt"))) {
                    String name = br.readLine();
                    String password = br.readLine();
                    String email = br.readLine();

                    BufferedReader br1 = new BufferedReader(new FileReader("genderRegistrationUser.txt"));
                    String age = br1.readLine();
                    String height = br1.readLine();
                    String weight = br1.readLine();
                    String gender = br1.readLine();

                    BufferedReader br2 = new BufferedReader(new FileReader("injuryRegistrationUser.txt"));
                    String hand = br2.readLine();
                    String leg = br2.readLine();
                    String  spinal = br2.readLine();
                    String head_and_neck = br2.readLine();
                    String other = br2.readLine();
                    out.println("REGISTER_USER_INJURY_LEVEL " + name + " " + password + " " + email + " "
                            + age + " " + height + " " + weight + " " + gender + " "
                            + hand + " " + leg + " " + spinal + " " + head_and_neck + " " + other + " "
                            +  r_bench_press_field.getText() + " " +  r_deadlift_field.getText() + " " +  r_squats_field.getText() + " "
                            +  w_bench_press_field.getText() + " " +  w_deadlift_field.getText() + " " +  w_squats_field.getText());
                } catch (IOException e) {e.printStackTrace();}
            } catch (IOException e) {e.printStackTrace();}

            try {ContinueAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });

        already_registered_button.setOnAction(event -> {
            try {AlreadyRegisteredAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });
    }
}
