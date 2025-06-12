package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.System;

public class RaportDanych {
    private int numerPaczki;
    private String akcja; // "OCENA" lub "REKLAMACJA"
    private String tresc;

    public RaportDanych(int numerPaczki, String akcja, String tresc) {
        this.numerPaczki = numerPaczki;
        this.akcja = akcja;
        this.tresc = tresc;
    }

    public RaportDanych() {
    }

    public static void zapiszDoPlikuRaportu(RaportDanych nowyRaport) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("daneDoRaportu.json");
        List<RaportDanych> raporty = new ArrayList<>();

        if (file.exists() && file.length() > 0) {
            try {
                raporty = mapper.readValue(file, new TypeReference<List<RaportDanych>>() {});
            } catch (IOException e) {
                System.err.println("Błąd przy odczycie istniejących danych: " + e.getMessage());
            }
        }

        boolean juzIstnieje = raporty.stream().anyMatch(r ->
                r.getNumerPaczki() == nowyRaport.getNumerPaczki() &&
                        r.getAkcja().equalsIgnoreCase("REKLAMACJA")
        );

        if (juzIstnieje) {
            System.out.println("Reklamacja dla tej paczki została już zgłoszona.");
            return;
        }

        raporty.add(nowyRaport);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, raporty);
        } catch (IOException e) {
            System.err.println("Błąd przy zapisie danych: " + e.getMessage());
        }
    }


    public int getNumerPaczki() {
        return numerPaczki;
    }

    public void setNumerPaczki(int numerPaczki) {
        this.numerPaczki = numerPaczki;
    }

    public String getAkcja() {
        return akcja;
    }

    public void setAkcja(String akcja) {
        this.akcja = akcja;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}