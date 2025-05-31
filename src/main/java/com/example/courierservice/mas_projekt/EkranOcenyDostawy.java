package com.example.courierservice.mas_projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EkranOcenyDostawy {

    @FXML
    private Button ekran_oceny_dostawy_powrot;

    @FXML
    private Button ekran_oceny_dostawy_ocena1;

    @FXML
    private Button ekran_oceny_dostawy_ocena2;

    @FXML
    private Button ekran_oceny_dostawy_ocena3;

    @FXML
    private Button ekran_oceny_dostawy_ocena4;

    @FXML
    private Button ekran_oceny_dostawy_ocena5;

    @FXML
    private Button ekran_oceny_zatwierdzanie;

    @FXML
    private TextArea ekran_oceny_komentarz;

    private int ocenaDostawyLiczbowo = 0;

    public void initialize() {
        ekran_oceny_dostawy_powrot.setOnAction(action -> powrot());
        ekran_oceny_dostawy_ocena1.setOnAction(action -> ocena(1));
        ekran_oceny_dostawy_ocena2.setOnAction(action -> ocena(2));
        ekran_oceny_dostawy_ocena3.setOnAction(action -> ocena(3));
        ekran_oceny_dostawy_ocena4.setOnAction(action -> ocena(4));
        ekran_oceny_dostawy_ocena5.setOnAction(action -> ocena(5));
        ekran_oceny_zatwierdzanie.setOnAction(action -> zatwierdzenieOcenyDostawy());

        ekran_oceny_komentarz.setText("");
        ekran_oceny_komentarz.setEditable(true);
    }

    @FXML
    private void powrot() {
        Main.switchScene("ekran-klienta.fxml");
    }

    @FXML
    private void ocena(int ocena) {
        this.ocenaDostawyLiczbowo = ocena;
        System.out.println("Wybrano ocenę: " + ocenaDostawyLiczbowo);
    }

    @FXML
    private void zatwierdzenieOcenyDostawy() {
        if (ocenaDostawyLiczbowo == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText("Brak oceny");
            alert.setContentText("Proszę wybrać ocenę przed zatwierdzeniem.");
            alert.showAndWait();
            return;
        }

        String komentarz = ekran_oceny_komentarz.getText().trim();

        System.out.println("Ocena: " + ocenaDostawyLiczbowo);
        if (!komentarz.isEmpty()) {
            System.out.println("Komentarz: " + komentarz);
        } else {
            System.out.println("Komentarz: brak");
        }

        Main.switchScene("ekran-klienta.fxml");
        // TODO: zapisz dane i zablokuj możliwość ponownej edycji
    }
}