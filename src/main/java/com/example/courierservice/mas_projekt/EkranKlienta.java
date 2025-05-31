package com.example.courierservice.mas_projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EkranKlienta {
    @FXML
    private Button ekran_klienta_powrot;

    @FXML
    private Button ekran_klienta_ocen_dostawe;

    @FXML
    private Button ekran_klienta_zwrot_paczki;

    @FXML
    private Label ekran_klienta_status_label;

    public void initialize() {
        ekran_klienta_powrot.setOnAction(event -> powrot());
        ekran_klienta_ocen_dostawe.setOnAction(event -> ocenaDostawy());
        ekran_klienta_zwrot_paczki.setOnAction(event -> zwrotPaczki());
    }

    private void powrot(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

    private void ocenaDostawy(){
        //TODO dopisać moduł oceny dostawy
    }

    private void zwrotPaczki(){
        //TODO dopisać tu co ma być XD
    }

    private void statusPaczki(){
        //TODO dopisać jak zrobić tu status
    }
}
