package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Kurier;
import com.example.courierservice.mas_projekt.Logowanie;
import com.example.courierservice.mas_projekt.Main;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EkranLogowaniaKuriera {
    @FXML
    private Button ekran_logowania_kuriera_zaloguj;

    @FXML
    private Button ekran_logowania_kuriera_wstecz;

    @FXML
    private PasswordField ekran_logowania_kuriera_haslo;

    @FXML
    private TextField ekran_logowania_kuriera_login;

    private int IDKurieraPoZalogowaniu;

    public void initialize() {
        ekran_logowania_kuriera_zaloguj.setOnAction(event -> zaloguj());
        ekran_logowania_kuriera_wstecz.setOnAction(event -> powrot());
    }

    private void zaloguj() {
        String login = ekran_logowania_kuriera_login.getText();
        String haslo = ekran_logowania_kuriera_haslo.getText();

        if (login.isEmpty() || haslo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd logowania!");
            alert.setHeaderText("Puste pola");
            alert.setContentText("Proszę wypełnić oba pola: login i hasło.");
            alert.showAndWait();
            return;
        }

        Logowanie logowanie = new Logowanie(login, haslo);
        IDKurieraPoZalogowaniu = logowanie.zalogujKurier(login, haslo);

        if (IDKurieraPoZalogowaniu > 0) {
            com.example.courierservice.mas_projekt.Session.setZalogowanyKurierId(IDKurieraPoZalogowaniu);

            Main.switchScene("ekran-kuriera.fxml");
        }
    }

    private void powrot() {
        com.example.courierservice.mas_projekt.Main.switchScene("ekran-glowny-logowania.fxml");
    }

    public int getIDKurieraPoZalogowaniu() {
        return IDKurieraPoZalogowaniu;
    }
}