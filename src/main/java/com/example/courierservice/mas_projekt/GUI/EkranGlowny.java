package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Klient;
import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Paczka;
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
        ekran_glowny_logowanie_kuriera.setOnAction(event -> otworzPanelKurier());
        ekran_glowny_logowanie_administratora.setOnAction(event -> otworzPanelAdmin());

        ekran_glowny_znajdz_paczke.setOnAction(e -> {
            try {
                int numer = Integer.parseInt(ekran_glowny_wpisz_nr_paczki.getText().trim());
                Paczka znaleziona = Klient.znajdzPaczkePoNumerze(numer);
                if (znaleziona != null) {
                    EkranKlienta.ustawPaczke(znaleziona);
                    Main.switchScene("ekran-klienta.fxml");
                    com.example.courierservice.mas_projekt.Session.setObecnaPaczkaId(numer);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Błąd");
                    alert.setHeaderText("Nie znaleziono paczki o numerze: " + numer + "");
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Błąd");
                alert.setHeaderText("Wprowadź poprawny numer paczki.");
                alert.showAndWait();
                return;
            }
        });
    }

    private void otworzPanelKurier() {
        Main.switchScene("ekran-logowania-kuriera.fxml");
    }

    private void otworzPanelAdmin() {
        Main.switchScene("ekran-logowania-administratora.fxml");
    }
}
