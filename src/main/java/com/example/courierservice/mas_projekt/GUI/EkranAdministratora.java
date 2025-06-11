package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert.AlertType;


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
    private ListView<String> ekran_administratora_lista_kurierow;

    @FXML
    private ListView<String> ekran_administratora_lista_pojazdow;

    private final ObjectMapper mapper = new ObjectMapper();
    private final String SCIEZKA_PACZKI = "paczki.json";
    private final String SCIEZKA_KURIERZY = "kurierzy.json";
    private final String SCIEZKA_POJAZDY = "pojazdy.json";

    public void initialize(){
        ekran_administratora_dodaj_paczke.setOnAction(event -> dodajPaczke());
        ekran_administratora_wyloguj.setOnAction(event -> wyloguj());
        ekran_administratora_usun_paczke.setOnAction(event -> usunPaczke());
        ekran_administratora_dodaj_kuriera.setOnAction(event -> dodajKuriera());
        ekran_administratora_usun_kuriera.setOnAction(event -> usunKuriera());
        ekran_administratora_zresetuj_haslo_kuriera.setOnAction(event -> zresetujHasloKuriera());
        ekran_administratora_dodaj_pojazd.setOnAction(event -> dodajPojazd());
        ekran_administratora_usun_pojazd.setOnAction(event -> usunPojazd());

        wczytajListePaczek();
        wczytajListeKurierow();
        wczytajListePojazdow();
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

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdzenie usunięcia");
                alert.setHeaderText(null);
                alert.setContentText("Czy na pewno chcesz usunąć paczkę #" + numerPaczki + "?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        administrator.usunPaczke(numerPaczki);
                        wczytajListePaczek();
                    }
                });
            }
        } else {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Brak wyboru");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Zaznacz paczkę do usunięcia.");
            infoAlert.showAndWait();
        }
    }

    private void dodajKuriera() {
        Main.switchScene("ekran-dodawania-kuriera.fxml");
    }

    private void usunKuriera() {
        Administrator administrator = new Administrator();
        String selected = ekran_administratora_lista_kurierow.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Pattern pattern = Pattern.compile("#(\\d+)");
            Matcher matcher = pattern.matcher(selected);

            if (matcher.find()) {
                int idKurier = Integer.parseInt(matcher.group(1));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdzenie usunięcia");
                alert.setHeaderText(null);
                alert.setContentText("Czy na pewno chcesz usunąć kuriera o ID #" + idKurier + "?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        administrator.usunKurier(idKurier);
                        wczytajListeKurierow();
                    }
                });
            }
        } else {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Brak wyboru");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Zaznacz kuriera do usunięcia.");
            infoAlert.showAndWait();
        }
    }


    private void wczytajListeKurierow() {
        File file = new File(SCIEZKA_KURIERZY);
        if (!file.exists()) {
            System.out.println("Plik kurierzy.json nie istnieje.");
            return;
        }

        try {
            List<Kurier> kurierzy = mapper.readValue(file, new TypeReference<List<Kurier>>() {});
            ekran_administratora_lista_kurierow.getItems().clear();
            for (Kurier k : kurierzy) {
                String opis = "#" + k.getId() + ": " +
                        k.getImie() + " " + k.getNazwisko() +
                        ", miasto: " + k.getMiastoObslugi();
                ekran_administratora_lista_kurierow.getItems().add(opis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zresetujHasloKuriera() {
        String wybranyKurier = ekran_administratora_lista_kurierow.getSelectionModel().getSelectedItem();

        if (wybranyKurier == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak wyboru");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz kuriera z listy, aby zresetować hasło.");
            alert.showAndWait();
            return;
        }

        try {
            Pattern pattern = Pattern.compile("#(\\d+):");
            Matcher matcher = pattern.matcher(wybranyKurier);

            if (!matcher.find()) {
                throw new IllegalArgumentException("Nieprawidłowy format wpisu listy kurierów.");
            }

            int id = Integer.parseInt(matcher.group(1));

            ObjectMapper mapper = new ObjectMapper();
            File plik = new File("kurierzy.json");
            List<Kurier> kurierzy = mapper.readValue(plik, new TypeReference<List<Kurier>>() {});

            boolean znaleziono = false;

            for (Kurier k : kurierzy) {
                if (k.getId() == id) {
                    k.setHaslo("1234");
                    znaleziono = true;
                    break;
                }
            }

            if (znaleziono) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(plik, kurierzy);

                Alert sukces = new Alert(Alert.AlertType.INFORMATION);
                sukces.setTitle("Sukces");
                sukces.setHeaderText(null);
                sukces.setContentText("Hasło zostało zresetowane!");
                sukces.showAndWait();
            } else {
                Alert blad = new Alert(Alert.AlertType.WARNING);
                blad.setTitle("Nie znaleziono");
                blad.setHeaderText(null);
                blad.setContentText("Nie znaleziono kuriera.");
                blad.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert blad = new Alert(Alert.AlertType.ERROR);
            blad.setTitle("Błąd");
            blad.setHeaderText(null);
            blad.setContentText("Wystąpił błąd podczas resetowania hasła.");
            blad.showAndWait();
        }
    }

    private void wczytajListePojazdow() {
        File file = new File(SCIEZKA_POJAZDY);
        if (!file.exists()) {
            System.out.println("Plik pojazdy.json nie istnieje.");
            return;
        }

        try {
            List<Pojazd> pojazdy = mapper.readValue(file, new TypeReference<List<Pojazd>>() {});
            ekran_administratora_lista_pojazdow.getItems().clear();
            for (Pojazd p : pojazdy) {
                String opis = "Model: " + p.getModel() +
                        ", Rejestracja: " + p.getNumerRejestracyjny() +
                        ", ID kuriera: " + p.getIDkuriera() ;
                ekran_administratora_lista_pojazdow.getItems().add(opis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dodajPojazd(){
        Main.switchScene("ekran-dodawania-pojazdu.fxml");
    }

    private void usunPojazd() {
        String selected = ekran_administratora_lista_pojazdow.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak wyboru");
            alert.setHeaderText(null);
            alert.setContentText("Zaznacz pojazd do usunięcia.");
            alert.showAndWait();
            return;
        }

        Pattern pattern = Pattern.compile("Rejestracja: ([^,]+)");
        Matcher matcher = pattern.matcher(selected);
        if (!matcher.find()) {
            System.out.println("Nie udało się dopasować numeru rejestracyjnego.");
            return;
        }

        String numerRejestracyjny = matcher.group(1);

        Alert potwierdzenie = new Alert(Alert.AlertType.CONFIRMATION);
        potwierdzenie.setTitle("Potwierdzenie usunięcia");
        potwierdzenie.setHeaderText(null);
        potwierdzenie.setContentText("Czy na pewno chcesz usunąć pojazd: " + numerRejestracyjny + "?");

        potwierdzenie.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    File plik = new File("pojazdy.json");
                    List<Pojazd> pojazdy = mapper.readValue(plik, new TypeReference<List<Pojazd>>() {});

                    boolean usunieto = pojazdy.removeIf(p -> p.getNumerRejestracyjny().equalsIgnoreCase(numerRejestracyjny));

                    if (usunieto) {
                        mapper.writerWithDefaultPrettyPrinter().writeValue(plik, pojazdy);
                        wczytajListePojazdow();

                        Alert sukces = new Alert(Alert.AlertType.INFORMATION);
                        sukces.setTitle("Sukces");
                        sukces.setHeaderText(null);
                        sukces.setContentText("Pojazd został usunięty.");
                        sukces.showAndWait();
                    } else {
                        Alert blad = new Alert(Alert.AlertType.WARNING);
                        blad.setTitle("Błąd");
                        blad.setHeaderText(null);
                        blad.setContentText("Nie znaleziono pojazdu do usunięcia.");
                        blad.showAndWait();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Alert blad = new Alert(Alert.AlertType.ERROR);
                    blad.setTitle("Błąd");
                    blad.setHeaderText(null);
                    blad.setContentText("Wystąpił problem podczas usuwania pojazdu.");
                    blad.showAndWait();
                }
            }
        });
    }
}