package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Administrator;
import com.example.courierservice.mas_projekt.Kurier;
import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Paczka;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EkranAdministratora {
    @FXML
    private Button ekran_administratora_dodaj_paczke;

    @FXML
    private Button ekran_administratora_usun_paczke;

    @FXML
    private Button ekran_administratora_dodaj_kuriera;

    @FXML
    private Button ekran_administratora_usun_kuriera;

    @FXML
    private Button ekran_administratora_dodaj_pojazd;

    @FXML
    private Button ekran_administratora_usun_pojazd;

    @FXML
    private Button ekran_administratora_edytuj_dane_kuriera;

    @FXML
    private Button ekran_administratora_zresetuj_haslo_kuriera;

    @FXML
    private Button ekran_administratora_wyloguj;

    @FXML
    private Button ekran_administratora_generuj_raport;

    @FXML
    private ListView<String> ekran_administratora_lista_paczek;

    @FXML
    private ListView<String> ekran_administratora_lista_kurierów;

    @FXML
    private ListView<String> ekran_administratora_lista_pojazdow;

    private final ObjectMapper mapper = new ObjectMapper();
    private final String SCIEZKA_PACZKI = "paczki.json";

    public void initialize(){
        ekran_administratora_dodaj_paczke.setOnAction(event -> dodajPaczke());
        ekran_administratora_wyloguj.setOnAction(event -> wyloguj());
        ekran_administratora_usun_paczke.setOnAction(event -> usunPaczke());

        wczytajListePaczek();
    }

    private void wyloguj(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

    private void dodajPaczke(){
        Main.switchScene("ekran-dodawania-paczki.fxml");
    }

    private void wczytajListePaczek() {
        File file = new File(SCIEZKA_PACZKI);
        if (!file.exists()) {
            System.out.println("Plik paczki.json nie istnieje.");
            return;
        }

        try {
            List<Paczka> paczki = mapper.readValue(file, new TypeReference<List<Paczka>>() {});
            ekran_administratora_lista_paczek.getItems().clear();
            for (Paczka p : paczki) {
                String opis = "#" + p.getNumerPaczki() + ": " +
                        p.getImieOdbiorcy() + " " + p.getNazwiskoOdbiorcy() +
                        ", " + p.getMiastoDostawy() +
                        ", status: " + p.getStatus() +
                        ", typ: " + p.getTyp();
                ekran_administratora_lista_paczek.getItems().add(opis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void usunPaczke() {
        Administrator administrator = new Administrator();
        String selected = ekran_administratora_lista_paczek.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Pattern pattern = Pattern.compile("#(\\d+)");
            Matcher matcher = pattern.matcher(selected);
            if (matcher.find()) {
                int numerPaczki = Integer.parseInt(matcher.group(1));
                administrator.usunPaczke(numerPaczki);
                wczytajListePaczek(); // odświeżenie
            }
        } else {
            System.out.println("Zaznacz paczkę do usunięcia.");
        }
    }

}