package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Kurier;
import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Pojazd;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EkranDodawaniaPojazdu {

    @FXML
    private Button ekran_dodawania_pojazdu_zatwierdz;

    @FXML
    private TextField ekran_dodawania_pojazdu_model;

    @FXML
    private TextField ekran_dodawania_pojazdu_rejestracja;

    @FXML
    private ComboBox<String> ekran_dodawania_pojazdu_kurier;

    private final ObjectMapper mapper = new ObjectMapper();
    private final String SCIEZKA_KURIERZY = "kurierzy.json";
    private final String SCIEZKA_POJAZDY = "pojazdy.json";

    @FXML
    public void initialize() {
        ekran_dodawania_pojazdu_zatwierdz.setOnAction(event -> dodajPojazd());
        wczytajKurierowDoComboBox();
    }

    private void wczytajKurierowDoComboBox() {
        try {
            List<Kurier> kurierzy = mapper.readValue(new File(SCIEZKA_KURIERZY), new TypeReference<List<Kurier>>() {});
            for (Kurier k : kurierzy) {
                String opis = "#" + k.getId() + ": " + k.getImie() + " " + k.getNazwisko() + " (" + k.getMiastoObslugi() + ")";
                ekran_dodawania_pojazdu_kurier.getItems().add(opis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dodajPojazd() {
        String model = ekran_dodawania_pojazdu_model.getText().trim();
        String rejestracja = ekran_dodawania_pojazdu_rejestracja.getText().trim();
        String wybranyKurier = (String) ekran_dodawania_pojazdu_kurier.getValue();

        if (model.isEmpty() || rejestracja.isEmpty() || wybranyKurier == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak danych");
            alert.setHeaderText(null);
            alert.setContentText("Uzupełnij wszystkie pola.");
            alert.showAndWait();
            return;
        }

        Pattern pattern = Pattern.compile("#(\\d+):");
        Matcher matcher = pattern.matcher(wybranyKurier);
        if (!matcher.find()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie udało się odczytać ID kuriera.");
            alert.showAndWait();
            return;
        }

        int kurierId = Integer.parseInt(matcher.group(1));

        try {
            File plik = new File(SCIEZKA_POJAZDY);
            List<Pojazd> pojazdy;

            if (plik.exists()) {
                pojazdy = mapper.readValue(plik, new TypeReference<List<Pojazd>>() {});
            } else {
                pojazdy = new ArrayList<>();
            }

            for (Pojazd p : pojazdy) {
                if (p.getNumerRejestracyjny().equalsIgnoreCase(rejestracja)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Duplikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Pojazd o takim numerze rejestracyjnym już istnieje.");
                    alert.showAndWait();
                    return;
                }
            }

            Pojazd nowyPojazd = new Pojazd(model, rejestracja, kurierId);
            pojazdy.add(nowyPojazd);

            mapper.writerWithDefaultPrettyPrinter().writeValue(plik, pojazdy);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukces");
            alert.setHeaderText(null);
            alert.setContentText("Dodano pojazd.");
            alert.showAndWait();

            ekran_dodawania_pojazdu_model.clear();
            ekran_dodawania_pojazdu_rejestracja.clear();
            ekran_dodawania_pojazdu_kurier.setValue(null);

            Main.switchScene("ekran-administratora.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}