package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Administrator;
import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Paczka;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EkranDodawaniaPaczki {

    @FXML private TextField ekran_dodawania_paczki_imie_odbiorcy;
    @FXML private TextField ekran_dodawania_paczki_nazwisko_odbiorcy;
    @FXML private TextField ekran_dodawania_paczki_miasto_odbiorcy;
    @FXML private TextField ekran_dodawania_paczki_adres_odbiorcy;
    @FXML private CheckBox ekran_dodawania_paczki_typ_zwykla;
    @FXML private CheckBox ekran_dodawania_paczki_typ_priorytetowa;
    @FXML private TextArea ekran_dodawania_paczki_opis;
    @FXML private Button ekran_dodawania_paczki_zatwierdz;

    private Administrator administrator = new Administrator();

    public void initialize() {
        ekran_dodawania_paczki_zatwierdz.setOnAction(e -> dodaj());
    }

    private void dodaj() {

        String imie = ekran_dodawania_paczki_imie_odbiorcy.getText().trim();
        String nazwisko = ekran_dodawania_paczki_nazwisko_odbiorcy.getText().trim();
        String adres = ekran_dodawania_paczki_adres_odbiorcy.getText().trim();
        String miasto = ekran_dodawania_paczki_miasto_odbiorcy.getText().trim();

        if (imie.isEmpty() || nazwisko.isEmpty() || adres.isEmpty() || miasto.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak danych");
            alert.setHeaderText("Niepełne dane paczki");
            alert.setContentText("Uzupełnij wszystkie wymagane pola: imię, nazwisko, adres, miasto.");
            alert.showAndWait();
            return;
        }

        String typ = null;
        if (ekran_dodawania_paczki_typ_zwykla.isSelected() && !ekran_dodawania_paczki_typ_priorytetowa.isSelected()) {
            typ = "Zwykła";
        } else if (!ekran_dodawania_paczki_typ_zwykla.isSelected() && ekran_dodawania_paczki_typ_priorytetowa.isSelected()) {
            typ = "Priorytetowa";
        } else {
            showAlert("Błąd typu", "Wybierz dokładnie jeden typ paczki.");
            return;
        }

        Paczka paczka = new Paczka(
                0, // numer zostanie nadany automatycznie
                ekran_dodawania_paczki_imie_odbiorcy.getText(),
                ekran_dodawania_paczki_nazwisko_odbiorcy.getText(),
                ekran_dodawania_paczki_miasto_odbiorcy.getText(),
                ekran_dodawania_paczki_adres_odbiorcy.getText(),
                "Przyjęta",
                typ,
                ekran_dodawania_paczki_opis.getText()
        );

        administrator.dodajPaczke(paczka);
        showAlert("Sukces", "Paczka dodana pomyślnie!");
        clearFields();
        Main.switchScene("ekran-administratora.fxml");
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        ekran_dodawania_paczki_imie_odbiorcy.clear();
        ekran_dodawania_paczki_nazwisko_odbiorcy.clear();
        ekran_dodawania_paczki_miasto_odbiorcy.clear();
        ekran_dodawania_paczki_adres_odbiorcy.clear();
        ekran_dodawania_paczki_typ_zwykla.setSelected(false);
        ekran_dodawania_paczki_typ_priorytetowa.setSelected(false);
        ekran_dodawania_paczki_opis.clear();
    }
}