package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EkranLogowaniaKuriera {
    @FXML
    private Button ekran_logowania_kuriera_zaloguj;

    @FXML
    private Button ekran_logowania_kuriera_wstecz;

    @FXML
    private PasswordField ekran_logowania_kuriera_haslo;

    @FXML
    private TextArea ekran_logowania_kuriera_login;

    public void initialize() {
        ekran_logowania_kuriera_zaloguj.setOnAction(event -> zaloguj());
        ekran_logowania_kuriera_wstecz.setOnAction(event -> powrot());
    }

    private void zaloguj(){
        String login = ekran_logowania_kuriera_login.getText();
        String haslo = ekran_logowania_kuriera_haslo.getText();
        if(login.isEmpty() || haslo.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd logowania!");
            alert.setHeaderText("Puste pola");
            alert.setContentText("Proszę wypełnić oba pola: login i hasło.");
            alert.showAndWait();
        }
        //TODO dodać opcje logowania do panelu kuriera
    }

    private void powrot(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

}
