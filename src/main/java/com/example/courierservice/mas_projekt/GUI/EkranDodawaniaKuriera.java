package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Administrator;
import com.example.courierservice.mas_projekt.Kurier;
import com.example.courierservice.mas_projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EkranDodawaniaKuriera {

    @FXML private Button ekran_dodawania_kuriera_zatwierdz;
    @FXML private TextField ekran_dodawania_kuriera_imie;
    @FXML private TextField ekran_dodawania_kuriera_nazwisko;
    @FXML private TextField ekran_dodawania_kuriera_miasto;

    private Administrator administrator = new Administrator();

    public void initialize() {
        ekran_dodawania_kuriera_zatwierdz.setOnAction(event -> zatwierdz());
    }

    private void zatwierdz() {
        String imie = ekran_dodawania_kuriera_imie.getText().trim();
        String nazwisko = ekran_dodawania_kuriera_nazwisko.getText().trim();
        String miasto = ekran_dodawania_kuriera_miasto.getText().trim();

        if (imie.isEmpty() || nazwisko.isEmpty() || miasto.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wszystkie pola muszą być uzupełnione.");
            alert.showAndWait();
            return;
        }

        Kurier nowyKurier = new Kurier(0, imie, nazwisko, miasto);
        administrator.dodajKuriera(imie, nazwisko, miasto);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Kurier został dodany.");
        alert.setContentText("Login: " + nowyKurier.getLogin() + "\nHasło: " + nowyKurier.getHaslo());
        alert.showAndWait();

        ekran_dodawania_kuriera_imie.clear();
        ekran_dodawania_kuriera_nazwisko.clear();
        ekran_dodawania_kuriera_miasto.clear();

        Main.switchScene("ekran-administratora.fxml");
    }
}
