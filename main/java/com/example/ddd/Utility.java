package com.example.ddd;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utility {
    // Example use:
    // Utility.changeToScene (getClass(), event, "myXLM.fxml");
    public static void changeToScene (Class aClass, Event aEvent, String sceneFileStr) throws Exception {
        Parent root = FXMLLoader.load (aClass.getResource(sceneFileStr));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)aEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /*already_registered_button.setOnAction(actionEvent -> {
        already_registered_button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    });*/
}