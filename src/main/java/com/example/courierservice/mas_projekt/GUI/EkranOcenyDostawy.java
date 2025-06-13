package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.OcenaDostawy;
import com.example.courierservice.mas_projekt.RaportDanych;
import com.example.courierservice.mas_projekt.Session;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.courierservice.mas_projekt.RaportDanych.zapiszDoPlikuRaportu;

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

    private int ocenaDostawyLiczbowo;

    public void initialize() {
        ekran_oceny_dostawy_powrot.setOnAction(action -> powrot());
        ekran_oceny_dostawy_ocena1.setOnAction(action -> ocena(1));
        ekran_oceny_dostawy_ocena2.setOnAction(action -> ocena(2));
        ekran_oceny_dostawy_ocena3.setOnAction(action -> ocena(3));
        ekran_oceny_dostawy_ocena4.setOnAction(action -> ocena(4));
        ekran_oceny_dostawy_ocena5.setOnAction(action -> ocena(5));

        ekran_oceny_komentarz.setEditable(true);

        ekran_oceny_zatwierdzanie.setOnAction(event -> zapiszOcene());
    }

    private void powrot() {
        Main.switchScene("ekran-klienta.fxml");
    }

    private void ocena(int ocena) {
        this.ocenaDostawyLiczbowo = ocena;
        resetujStylePrzyciskow();

        String stylAktywny = "-fx-background-color: #444444; -fx-text-fill: white;";

        switch (ocena) {
            case 1 -> ekran_oceny_dostawy_ocena1.setStyle(stylAktywny);
            case 2 -> ekran_oceny_dostawy_ocena2.setStyle(stylAktywny);
            case 3 -> ekran_oceny_dostawy_ocena3.setStyle(stylAktywny);
            case 4 -> ekran_oceny_dostawy_ocena4.setStyle(stylAktywny);
            case 5 -> ekran_oceny_dostawy_ocena5.setStyle(stylAktywny);
        }
    }

    private void resetujStylePrzyciskow() {
        String stylDomyslny = "-fx-background-color: transparent;";
        ekran_oceny_dostawy_ocena1.setStyle(stylDomyslny);
        ekran_oceny_dostawy_ocena2.setStyle(stylDomyslny);
        ekran_oceny_dostawy_ocena3.setStyle(stylDomyslny);
        ekran_oceny_dostawy_ocena4.setStyle(stylDomyslny);
        ekran_oceny_dostawy_ocena5.setStyle(stylDomyslny);
    }

    private void zapiszOcene() {
        String komentarz = ekran_oceny_zatwierdzanie.getText().trim();

        String tresc = ocenaDostawyLiczbowo + ", " + komentarz;

        RaportDanych raport = new RaportDanych(Session.getObecnaPaczkaId(), "OCENA", tresc);

        zapiszDoPlikuRaportu(raport);

        Main.switchScene("ekran-klienta.fxml");
    }
}