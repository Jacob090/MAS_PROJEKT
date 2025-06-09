package com.example.courierservice.mas_projekt;

import com.example.courierservice.mas_projekt.Administrator;
import com.example.courierservice.mas_projekt.Klient;
import com.example.courierservice.mas_projekt.Kurier;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.System;

public abstract class Uzytkownik {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String PLIK_KURIERZY = "kurierzy.json";
    private static final String PLIK_KLIENCI = "klienci.json";
    private static final String PLIK_ADMINISTRATORZY = "administratorzy.json";

    protected int id;
    protected String imie;
    protected String nazwisko;

    public Uzytkownik(String imie, String nazwisko) {
        this.id = generateUniqueId();
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Uzytkownik(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    private static int generateUniqueId() {
        Set<Integer> istniejąceId = new HashSet<>();

        istniejąceId.addAll(loadIds(PLIK_KURIERZY, new TypeReference<List<Kurier>>() {}));
        istniejąceId.addAll(loadIds(PLIK_KLIENCI, new TypeReference<List<Klient>>() {}));
        istniejąceId.addAll(loadIds(PLIK_ADMINISTRATORZY, new TypeReference<List<Administrator>>() {}));

        int newId = 1;
        while (istniejąceId.contains(newId)) {
            newId++;
        }
        return newId;
    }

    private static <T extends Uzytkownik> Set<Integer> loadIds(String filePath, TypeReference<List<T>> typeRef) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                List<T> users = mapper.readValue(file, typeRef);
                Set<Integer> ids = new HashSet<>();
                for (T user : users) {
                    ids.add(user.getId());
                }
                return ids;
            }
        } catch (Exception e) {
            System.out.println("Błąd przy odczycie ID z " + filePath);
        }
        return new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return String.format("%s %s (ID: %d)", imie, nazwisko, id);
    }
}