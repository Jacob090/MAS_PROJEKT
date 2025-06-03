package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends Uzytkownik {
    private String haslo;

    private static final String PACZKI_JSON = "paczki.json";
    private final ObjectMapper mapper = new ObjectMapper();


    public Administrator(int id, String imie, String nazwisko, String haslo) {
        super(id, imie, nazwisko);
        this.haslo = haslo;
    }

    public Administrator() {
        super(0, "", "");  // albo domyślne wartości np. ID=0
        this.haslo = "";
    }


    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void dodajPaczke(Paczka paczka) {
        try {
            List<Paczka> paczki = new ArrayList<>();
            File file = new File(PACZKI_JSON);
            if (file.exists()) {
                paczki = mapper.readValue(file, new TypeReference<List<Paczka>>() {});
            }

            // automatyczne ID
            int maxId = paczki.stream().mapToInt(Paczka::getNumerPaczki).max().orElse(0);
            paczka.setNumerPaczki(maxId + 1);

            paczki.add(paczka);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, paczki);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usunPaczke(int numerPaczki) {
        try {
            File file = new File(PACZKI_JSON);
            List<Paczka> paczki = new ArrayList<>();

            if (file.exists()) {
                paczki = mapper.readValue(file, new TypeReference<List<Paczka>>() {});
            }

            paczki.removeIf(p -> p.getNumerPaczki() == numerPaczki);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, paczki);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
