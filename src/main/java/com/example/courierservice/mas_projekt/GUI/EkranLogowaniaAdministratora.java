package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EkranLogowaniaAdministratora {
    @FXML
    private Button ekran_logowania_administratora_zaloguj;

    @FXML
    private Button ekran_logowania_administratora_wstecz;

    @FXML
    private PasswordField ekran_logowania_administratora_haslo;

    @FXML
    private TextField ekran_logowania_administratora_login;

    public void initialize() {
        ekran_logowania_administratora_zaloguj.setOnAction(event -> zaloguj());
        ekran_logowania_administratora_wstecz.setOnAction(event -> powrot());
    }

    private void zaloguj(){
        String login = ekran_logowania_administratora_login.getText();
        String haslo = ekran_logowania_administratora_haslo.getText();
        if(login.isEmpty() || haslo.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd logowania!");
            alert.setHeaderText("Puste pola");
            alert.setContentText("Proszę wypełnić oba pola: login i hasło.");
            alert.showAndWait();
        } else if (login.equals("admin") && haslo.equals("admin")) {
            Main.switchScene("ekran-administratora.fxml");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd logowania!");
            alert.setHeaderText("Wprowadzono niepoprawne dane!");
            alert.setContentText("Proszę podać poprawny login i hasło.");
            alert.showAndWait();
        }
    }

    private void powrot(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

}
