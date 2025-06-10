package com.example.courierservice.mas_projekt;

import com.example.courierservice.mas_projekt.Administrator;
import com.example.courierservice.mas_projekt.Klient;
import com.example.courierservice.mas_projekt.Kurier;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
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

    public static int generateUniqueId() {
        Set<Integer> istniejąceId = new HashSet<>();

        istniejąceId.addAll(Uzytkownik.<Kurier>loadIds("kurierzy.json", new TypeReference<List<Kurier>>() {}));
        istniejąceId.addAll(Uzytkownik.<Klient>loadIds("klienci.json", new TypeReference<List<Klient>>() {}));
        istniejąceId.addAll(Uzytkownik.<Administrator>loadIds("administratorzy.json", new TypeReference<List<Administrator>>() {}));

        int newId = 1;
        while (istniejąceId.contains(newId)) {
            newId++;
        }
        return newId;
    }

    private static <T extends Uzytkownik> Set<Integer> loadIds(String filePath, TypeReference<List<T>> typeReference) {
        File file = new File(filePath);
        if (!file.exists()) {
            return Collections.emptySet();
        }

        try {
            List<T> lista = mapper.readValue(file, typeReference);
            Set<Integer> ids = new HashSet<>();
            for (T u : lista) {
                ids.add(u.id);
            }
            return ids;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
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