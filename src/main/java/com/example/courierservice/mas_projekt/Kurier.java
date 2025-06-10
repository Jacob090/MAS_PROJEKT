package com.example.courierservice.mas_projekt;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.lang.System;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Kurier extends Uzytkownik {
    private String login;
    private String haslo;
    private String miastoObslugi;

    public Kurier(int id, String imie, String nazwisko, String miastoObslugi) {
        super(id, imie, nazwisko);
        this.login = generujLogin(imie, nazwisko);
        this.haslo = "1234";
        this.miastoObslugi = miastoObslugi;
    }

    public Kurier() {
        super(0, "", "");
        this.login = "";
        this.haslo = "1234";
        this.miastoObslugi = "";
    }

    public void zmienHaslo(String noweHaslo) {
        ObjectMapper mapper = new ObjectMapper();
        File plik = new File("kurierzy.json");

        try {
            List<Kurier> kurierzy = mapper.readValue(plik, new TypeReference<List<Kurier>>() {});
            for (Kurier k : kurierzy) {
                if (k.getId() == this.getId()) {
                    k.setHaslo(noweHaslo);
                    break;
                }
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(plik, kurierzy);
            System.out.println("Hasło zostało zmienione.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generujLogin(String imie, String nazwisko) {
        return imie.toLowerCase().replaceAll("\\s+", "") + "." + nazwisko.toLowerCase().replaceAll("\\s+", "");
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getMiastoObslugi() {
        return miastoObslugi;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setMiastoObslugi(String miastoObslugi) {
        this.miastoObslugi = miastoObslugi;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Kurier: %s %s (ID: %d, login: %s, miasto: %s)", imie, nazwisko, id, login, miastoObslugi);
    }
}