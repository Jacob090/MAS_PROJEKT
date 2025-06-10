package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.System;

public class Administrator extends Uzytkownik {
    private String haslo;

    private static final String KURIERZY_JSON = "kurierzy.json";
    private static final String PACZKI_JSON = "paczki.json";
    private final ObjectMapper mapper = new ObjectMapper();


    public Administrator(int id, String imie, String nazwisko, String haslo) {
        super(id, imie, nazwisko);
        this.haslo = haslo;
    }

    public Administrator() {
        super(0, "", "");
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

    public void dodajKuriera(String imie, String nazwisko, String miastoObslugi) {
        try {
            List<Kurier> kurierzy = new ArrayList<>();
            File file = new File(KURIERZY_JSON);

            if (file.exists()) {
                kurierzy = mapper.readValue(file, new TypeReference<List<Kurier>>() {});
            }

            Kurier nowyKurier = new Kurier(Uzytkownik.generateUniqueId(), imie, nazwisko, miastoObslugi);
            kurierzy.add(nowyKurier);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, kurierzy);
            System.out.println("Dodano nowego kuriera: " + nowyKurier);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usunKurier(int idKurier) {
        try {
            File file = new File(KURIERZY_JSON);
            List<Kurier> kurierzy = new ArrayList<>();

            if (file.exists()) {
                kurierzy = mapper.readValue(file, new TypeReference<List<Kurier>>() {});
            }

            boolean usunieto = kurierzy.removeIf(k -> k.getId() == idKurier);

            if (usunieto) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, kurierzy);
                System.out.println("Usunięto kuriera o ID: " + idKurier);
            } else {
                System.out.println("Nie znaleziono kuriera o ID: " + idKurier);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
