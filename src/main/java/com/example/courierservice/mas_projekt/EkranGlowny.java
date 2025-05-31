package com.example.courierservice.mas_projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Scanner;

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
        System.out.println("Kliknięto: Kurier");
        // TODO: załaduj widok kuriera
    }

    private void otworzPanelAdmin() {
        System.out.println("Kliknięto: Administrator");
        // TODO: załaduj widok administratora
    }

    private void szukanaPaczka() {
        String numerPaczki = ekran_glowny_wpisz_nr_paczki.getText();
        System.out.println("Wpisany numer paczki: " + numerPaczki);
    }
}
