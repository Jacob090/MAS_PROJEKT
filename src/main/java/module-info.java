module com.example.courierservice.mas_projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;


    opens com.example.courierservice.mas_projekt to javafx.fxml;
    exports com.example.courierservice.mas_projekt;
    exports com.example.courierservice.mas_projekt.GUI;
    opens com.example.courierservice.mas_projekt.GUI to javafx.fxml;
}