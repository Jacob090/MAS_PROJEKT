package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class EkranReklamacji {

    int numerPaczki = 1234567890;

    @FXML
    private Button ekran_reklamacji_powrot;

    @FXML
    private Button ekran_reklamacji_wyslij_zgloszenie;

    @FXML
    private Label ekran_reklamacji_numer_paczki;

    @FXML
    private TextArea ekran_reklamacji_opis_reklamacji;

    public void initialize() {
        ekran_reklamacji_powrot.setOnAction(action -> powrot());
        ekran_reklamacji_wyslij_zgloszenie.setOnAction(action -> zapiszReklamacje());
        ekran_reklamacji_numer_paczki.setText(String.valueOf(numerPaczki));
    }

    @FXML
    private void powrot() {
        Main.switchScene("ekran-klienta.fxml");
    }

    @FXML
    public void wypiszNumerPaczki() {
        System.out.println("Numer paczki: " + numerPaczki);
    }

    @FXML
    private void zapiszReklamacje() {
        String opis = ekran_reklamacji_opis_reklamacji.getText();
        System.out.println("Zapisano reklamację: " + opis);
        Main.switchScene("ekran-klienta.fxml");
    }
}