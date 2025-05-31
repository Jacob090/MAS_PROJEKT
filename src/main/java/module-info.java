module com.example.courierservice.mas_projekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.courierservice.mas_projekt to javafx.fxml;
    exports com.example.courierservice.mas_projekt;
}