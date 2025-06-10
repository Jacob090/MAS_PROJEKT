package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.lang.System;

public class Logowanie {
    private String login;
    private String haslo;

    private static final String PLIK_KURIERZY = "kurierzy.json";
    private static final String PLIK_PACZKI = "paczki.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public Logowanie(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public Kurier zalogujKurier() {
        try {
            String[] dane = login.split("\\.");
            if (dane.length != 2) return null;

            String imie = dane[0];
            String nazwisko = dane[1];

            List<Kurier> kurierzy = mapper.readValue(new File(PLIK_KURIERZY), new TypeReference<List<Kurier>>() {});
            for (Kurier kurier : kurierzy) {
                if (kurier.getImie().equalsIgnoreCase(imie) && kurier.getNazwisko().equalsIgnoreCase(nazwisko)) {
                    Main.switchScene("ekran-kuriera.fxml");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}