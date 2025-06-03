package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Paczka;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.scene.control.ListView;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class EkranKuriera {

    @FXML
    private Button ekran_kuriera_wyloguj;

    @FXML
    private Button ekran_kuriera_zapisz;

    @FXML
    private Button ekran_kuriera_status_dostarczona;

    @FXML
    private Button ekran_kuriera_status_zwrot;

    @FXML
    private Button ekran_kuriera_zmien_haslo;

    @FXML
    private ListView<String> ekran_kuriera_lista_paczek;

    @FXML
    private TextArea ekran_kuriera_opis;

    private final ObjectMapper mapper = new ObjectMapper();
    private final String SCIEZKA_PACZKI = "paczki.json";

    @FXML
    public void initialize(){
        ekran_kuriera_wyloguj.setOnAction(action -> wyloguj());
        ekran_kuriera_zapisz.setOnAction(action -> zapisz());
        ekran_kuriera_zmien_haslo.setOnAction(action -> zmienHaslo());

        wczytajListePaczek();
    }

    private void wczytajListePaczek() {
        File file = new File(SCIEZKA_PACZKI);
        if (!file.exists()) {
            System.out.println("Plik paczki.json nie istnieje.");
            return;
        }

        try {
            List<Paczka> paczki = mapper.readValue(file, new TypeReference<List<Paczka>>() {});
            ekran_kuriera_lista_paczek.getItems().clear();
            for (Paczka p : paczki) {
                String opis = "#" + p.getNumerPaczki() + ": " +
                        p.getImieOdbiorcy() + " " + p.getNazwiskoOdbiorcy() +
                        ", " + p.getMiastoDostawy() +
                        ", status: " + p.getStatus() +
                        ", typ: " + p.getTyp();

                if (p.getNotatka() != null && !p.getNotatka().isBlank()) {
                    opis += ", notatka: " + p.getNotatka();
                }

                ekran_kuriera_lista_paczek.getItems().add(opis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wyloguj(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

    public void zapisz(){

    }
    public void zmienHaslo(){

    }
}
