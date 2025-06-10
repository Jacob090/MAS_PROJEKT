package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Kurier;
import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Paczka;
import com.example.courierservice.mas_projekt.Session;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private int zalogowanyKurier = Session.getZalogowanyKurierId();

    @FXML
    public void initialize() {
        ekran_kuriera_wyloguj.setOnAction(action -> wyloguj());
        ekran_kuriera_zapisz.setOnAction(action -> zapisz());
        ekran_kuriera_zmien_haslo.setOnAction(action -> zmienHaslo());
        ekran_kuriera_status_dostarczona.setOnAction(action -> zmienStatus("Dostarczona"));
        ekran_kuriera_status_zwrot.setOnAction(action -> zmienStatus("Zwrot"));

        wczytajListePaczek();
    }

    private void wczytajListePaczek() {
        try {
            File kurierzyPlik = new File("kurierzy.json");
            if (!kurierzyPlik.exists()) return;

            List<Kurier> kurierzy = mapper.readValue(kurierzyPlik, new TypeReference<List<Kurier>>() {});
            String miastoObslugi = kurierzy.stream()
                    .filter(k -> k.getId() == zalogowanyKurier)
                    .map(Kurier::getMiastoObslugi)
                    .findFirst()
                    .orElse(null);

            if (miastoObslugi == null) {
                System.out.println("Nie znaleziono kuriera o ID: " + zalogowanyKurier);
                return;
            }

            File paczkiPlik = new File(SCIEZKA_PACZKI);
            if (!paczkiPlik.exists()) return;

            List<Paczka> paczki = mapper.readValue(paczkiPlik, new TypeReference<List<Paczka>>() {});
            ekran_kuriera_lista_paczek.getItems().clear();

            for (Paczka paczka : paczki) {
                if (paczka.getMiastoDostawy().equalsIgnoreCase(miastoObslugi)) {
                    String opis = "#" + paczka.getNumerPaczki() + ": " +
                            paczka.getImieOdbiorcy() + " " + paczka.getNazwiskoOdbiorcy() +
                            ", " + paczka.getAdresDostawy() +
                            ", status: " + paczka.getStatus() +
                            ", typ: " + paczka.getTyp() +
                            ", opis: " + paczka.getNotatka();
                    ekran_kuriera_lista_paczek.getItems().add(opis);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zmienHaslo() {
        int idKurier = Session.getZalogowanyKurierId();

        if (idKurier == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Brak zalogowanego kuriera.");
            alert.showAndWait();
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Zmiana hasła");
        dialog.setHeaderText("Podaj nowe hasło");
        dialog.setContentText("Nowe hasło:");

        dialog.showAndWait().ifPresent(noweHaslo -> {
            if (noweHaslo.trim().isEmpty()) {
                Alert blad = new Alert(Alert.AlertType.WARNING);
                blad.setTitle("Błąd");
                blad.setHeaderText(null);
                blad.setContentText("Hasło nie może być puste.");
                blad.showAndWait();
                return;
            }

            try {
                ObjectMapper mapper = new ObjectMapper();
                File plik = new File("kurierzy.json");

                List<Kurier> kurierzy = mapper.readValue(plik, new TypeReference<List<Kurier>>() {});
                boolean znaleziono = false;

                for (Kurier k : kurierzy) {
                    if (k.getId() == idKurier) {
                        k.setHaslo(noweHaslo);
                        znaleziono = true;
                        break;
                    }
                }

                if (znaleziono) {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(plik, kurierzy);

                    Alert ok = new Alert(Alert.AlertType.INFORMATION);
                    ok.setTitle("Sukces");
                    ok.setHeaderText(null);
                    ok.setContentText("Hasło zostało zmienione.");
                    ok.showAndWait();
                } else {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("Błąd");
                    err.setHeaderText(null);
                    err.setContentText("Nie znaleziono kuriera.");
                    err.showAndWait();
                }

            } catch (IOException e) {
                e.printStackTrace();
                Alert blad = new Alert(Alert.AlertType.ERROR);
                blad.setTitle("Błąd");
                blad.setHeaderText(null);
                blad.setContentText("Wystąpił błąd podczas zmiany hasła.");
                blad.showAndWait();
            }
        });
    }

    private void zmienStatus(String status) {
        String wybranyElement = ekran_kuriera_lista_paczek.getSelectionModel().getSelectedItem();

        if (wybranyElement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak wyboru");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz paczkę z listy, aby zmienić jej status.");
            alert.showAndWait();
            return;
        }

        try {
            int numerPaczki = Integer.parseInt(wybranyElement.split(":")[0].replace("#", "").trim());

            ObjectMapper mapper = new ObjectMapper();
            File plik = new File("paczki.json");

            List<Paczka> paczki = mapper.readValue(plik, new TypeReference<List<Paczka>>() {});
            boolean znaleziono = false;

            for (Paczka p : paczki) {
                if (p.getNumerPaczki() == numerPaczki) {
                    p.setStatus(status);
                    znaleziono = true;
                    break;
                }
            }

            if (znaleziono) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(plik, paczki);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Status paczki został zmieniony.");
                alert.showAndWait();

                wczytajListePaczek();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Nie znaleziono paczki w pliku.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas zmiany statusu paczki.");
            alert.showAndWait();
        }
    }


    public void wyloguj() {
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

    public void zapisz() {
        String wybranyElement = ekran_kuriera_lista_paczek.getSelectionModel().getSelectedItem();
        String wpisanyOpis = ekran_kuriera_opis.getText();

        if (wybranyElement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak wyboru");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz paczkę z listy, aby zapisać opis.");
            alert.showAndWait();
            return;
        }

        if (wpisanyOpis.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak treści");
            alert.setHeaderText(null);
            alert.setContentText("Pole opisu jest puste.");
            alert.showAndWait();
            return;
        }

        try {
            int numerPaczki = Integer.parseInt(wybranyElement.split(":")[0].replace("#", "").trim());

            ObjectMapper mapper = new ObjectMapper();
            File plik = new File("paczki.json");

            List<Paczka> paczki = mapper.readValue(plik, new TypeReference<List<Paczka>>() {});
            boolean znaleziono = false;

            for (Paczka p : paczki) {
                if (p.getNumerPaczki() == numerPaczki) {
                    p.setNotatka(wpisanyOpis);
                    znaleziono = true;
                    break;
                }
            }

            if (znaleziono) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(plik, paczki);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Zapisano");
                alert.setHeaderText(null);
                alert.setContentText("Opis został zapisany.");
                alert.showAndWait();

                ekran_kuriera_opis.clear();
                wczytajListePaczek();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Nie znaleziono paczki.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Wystąpił błąd podczas zapisu opisu.");
            alert.showAndWait();
        }
    }
}