package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Alert;

public class Logowanie {
    private String login;
    private String haslo;

    private static final String PLIK_KURIERZY = "kurierzy.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public Logowanie(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public int zalogujKurier(String login, String haslo) {
        try {
            List<Kurier> kurierzy = mapper.readValue(new File(PLIK_KURIERZY), new TypeReference<List<Kurier>>() {});
            for (Kurier kurier : kurierzy) {
                if (kurier.getLogin().equalsIgnoreCase(login) && kurier.getHaslo().equals(haslo)) {
                    return kurier.getId();
                }
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd logowania");
            alert.setHeaderText(null);
            alert.setContentText("Niepoprawny login lub hasło. Spróbuj ponownie.");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}