package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.RaportDanych;
import com.example.courierservice.mas_projekt.Session;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.courierservice.mas_projekt.RaportDanych.zapiszDoPlikuRaportu;

public class EkranReklamacji {

    @FXML
    private Button ekran_reklamacji_powrot;

    @FXML
    private Button ekran_reklamacji_wyslij_zgloszenie;

    @FXML
    private Label ekran_reklamacji_numer_paczki;

    @FXML
    private TextArea ekran_reklamacji_opis_reklamacji;

    private final ObjectMapper mapper = new ObjectMapper();

    public void initialize() {
        ekran_reklamacji_powrot.setOnAction(action -> powrot());
        ekran_reklamacji_numer_paczki.setText("   " + String.valueOf(Session.getObecnaPaczkaId()));
        ekran_reklamacji_wyslij_zgloszenie.setOnAction(event -> zapiszReklamacje());
    }

    @FXML
    private void powrot() {
        Main.switchScene("ekran-klienta.fxml");
    }

    private void zapiszReklamacje() {
        String komentarz = ekran_reklamacji_opis_reklamacji.getText().trim();

        String tresc = komentarz;

        RaportDanych raport = new RaportDanych(Session.getObecnaPaczkaId(), "REKLAMACJA", tresc);

        zapiszDoPlikuRaportu(raport);

        Main.switchScene("ekran-klienta.fxml");
    }}