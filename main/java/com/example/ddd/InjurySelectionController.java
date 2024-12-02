package com.example.ddd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import animations.HighlightingBig;
import animations.HighlightingSmall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

public class InjurySelectionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button already_registered_button;

    @FXML
    private Button continue_button;

    @FXML
    private ComboBox hand_button;

    @FXML
    private ComboBox head_and_neck_button;

    @FXML
    private ComboBox leg_button;

    @FXML
    private ComboBox other_button;

    @FXML
    private ComboBox spinal_button;

    @FXML
    void AlreadyRegisteredAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "hello-view.fxml");
    }

    @FXML
    void ContinueAction(ActionEvent event) throws Exception {
        Utility.changeToScene (getClass(), event, "level_selection-view.fxml");
    }

    @FXML
    void HandAction(ActionEvent event) {
    }

    @FXML
    void HeadAndNeckAction(ActionEvent event) {
    }

    @FXML
    void LegAction(ActionEvent event) {
    }

    @FXML
    void OtherAction(ActionEvent event) {
    }

    @FXML
    void SpinalAction(ActionEvent event) {
    }

    private void color(ComboBox a){
        a.setButtonCell(new ListCell<String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(item);
                setTextFill(Color.rgb(179,179,179));
            }
        });
    }
    @FXML
    void initialize() {
        HighlightingBig highlighting_continue_button = new HighlightingBig(continue_button);
        HighlightingSmall highlighting_already_registered_button = new HighlightingSmall(already_registered_button);

        ObservableList<String> hand_list = FXCollections.observableArrayList("Переломы плеча", "Переломы предплечья", "Переломы кисти", "Растяжения", "Разрывы сухожилий", "Травмы локтевого сустава", "Травмы запястья", "Нет");
        hand_button.setItems(hand_list);
        color(hand_button);

        ObservableList<String> leg_list = FXCollections.observableArrayList("Переломы", "Растяжения", "Разрывы связок", "Травмы коленного сустава", "Травмы голеностопного сустава", "Нет");
        leg_button.setItems(leg_list);
        color(leg_button);

        ObservableList<String> spinal_list = FXCollections.observableArrayList("Переломы", "Повреждения дисков", "Растяжения", "Смещения позвонков", "Нет");
        spinal_button.setItems(spinal_list);
        color(spinal_button);

        ObservableList<String> head_and_neck_list = FXCollections.observableArrayList("Сотрясение мозга", "Ушибы и раны головы", "Повреждения шейных позвонков", "Травмы лицевой области", "Нет");
        head_and_neck_button.setItems(head_and_neck_list);
        color(head_and_neck_button);

        ObservableList<String> other_list = FXCollections.observableArrayList("Повреждение внутренних органов", "Ожоги и обморожения", "Нет");
        other_button.setItems(other_list);
        color(other_button);

        already_registered_button.setOnAction(event -> {
            try {AlreadyRegisteredAction(event);}
            catch (Exception e) {throw new RuntimeException(e);}
        });

        continue_button.setOnAction(event -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("injuryRegistrationUser.txt"));
                writer.write(hand_button.getSelectionModel().getSelectedItem().toString().replaceAll(" ", ""));
                writer.newLine();
                writer.write(leg_button.getSelectionModel().getSelectedItem().toString().replaceAll(" ", ""));
                writer.newLine();
                writer.write(spinal_button.getSelectionModel().getSelectedItem().toString().replaceAll(" ", ""));
                writer.newLine();
                writer.write(head_and_neck_button.getSelectionModel().getSelectedItem().toString().replaceAll(" ", ""));
                writer.newLine();
                writer.write(other_button.getSelectionModel().getSelectedItem().toString().replaceAll(" ", ""));

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