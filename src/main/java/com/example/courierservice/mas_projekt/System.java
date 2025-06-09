package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class System {

    private static final String PACZKI_FILE = "paczki.json";
    private static final String KURIERZY_FILE = "kurierzy.json";
    private static final String KLIENCI_FILE = "klienci.json";

    private final ObjectMapper mapper = new ObjectMapper();

    private List<Paczka> paczki = new ArrayList<>();
    private List<Kurier> kurierzy = new ArrayList<>();
    private List<Klient> klienci = new ArrayList<>();

    public System() {
        wczytajPaczki();
        wczytajKurierow();
        wczytajKlientow();
    }

    public List<Paczka> getPaczki() {
        return paczki;
    }

    public List<Kurier> getKurierzy() {
        return kurierzy;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public void dodajPaczke(Paczka paczka) {
        // automatyczny numer
        int max = paczki.stream().mapToInt(Paczka::getNumerPaczki).max().orElse(0);
        paczka.setNumerPaczki(max + 1);
        paczki.add(paczka);
        zapiszPaczki();
    }

    public void dodajKurier(Kurier kurier) {
        kurierzy.add(kurier);
        zapiszKurierow();
    }

    public void dodajKlienta(Klient klient) {
        klienci.add(klient);
        zapiszKlientow();
    }

    public void zapiszPaczki() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PACZKI_FILE), paczki);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zapiszKurierow() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(KURIERZY_FILE), kurierzy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zapiszKlientow() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(KLIENCI_FILE), klienci);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wczytajPaczki() {
        try {
            File file = new File(PACZKI_FILE);
            if (file.exists()) {
                paczki = mapper.readValue(file, new TypeReference<List<Paczka>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wczytajKurierow() {
        try {
            File file = new File(KURIERZY_FILE);
            if (file.exists()) {
                kurierzy = mapper.readValue(file, new TypeReference<List<Kurier>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wczytajKlientow() {
        try {
            File file = new File(KLIENCI_FILE);
            if (file.exists()) {
                klienci = mapper.readValue(file, new TypeReference<List<Klient>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}