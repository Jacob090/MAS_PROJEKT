package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EkranGlowny {

    @FXML
    private Button ekran_glowny_logowanie_kuriera;

    @FXML
    private Button ekran_glowny_logowanie_administratora;

    @FXML
    private Button ekran_glowny_znajdz_paczke;

    @FXML
    private TextField ekran_glowny_wpisz_nr_paczki;

    @FXML
    public void initialize() {
        ekran_glowny_znajdz_paczke.setOnAction(event -> znajdzPrzesylke());
        ekran_glowny_logowanie_kuriera.setOnAction(event -> otworzPanelKurier());
        ekran_glowny_logowanie_administratora.setOnAction(event -> otworzPanelAdmin());
        ekran_glowny_wpisz_nr_paczki.setOnAction(Event -> szukanaPaczka());
    }

    private void znajdzPrzesylke() {
        szukanaPaczka();
    }

    private void otworzPanelKurier() {
        Main.switchScene("ekran-logowania-kuriera.fxml");
    }

    private void otworzPanelAdmin() {
        Main.switchScene("ekran-logowania-administratora.fxml");
    }

    private void szukanaPaczka() {
        String numerPaczki = ekran_glowny_wpisz_nr_paczki.getText();
        if(numerPaczki.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błędny numer paczki");
            alert.setHeaderText("Prosze wpisać poprawny numer paczki!");
            alert.showAndWait();
        }
    }
}
