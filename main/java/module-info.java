module com.example.ddd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.ddd to javafx.fxml;
    exports com.example.ddd;
    exports server;
    opens server to javafx.fxml;
}