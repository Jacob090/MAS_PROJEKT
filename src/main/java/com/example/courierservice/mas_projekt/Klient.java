package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Klient extends Uzytkownik {
    private String adres;
    private String miasto;
    private String telefon;
    private String email;

    public Klient(int id, String imie, String nazwisko, String adres, String telefon, String email, String miasto) {
        super(id, imie, nazwisko);
        this.adres = adres;
        this.telefon = telefon;
        this.email = email;
        this.miasto = miasto;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public static Paczka znajdzPaczkePoNumerze(int numerPaczki) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("paczki.json");

        try {
            if (!file.exists()) return null;

            List<Paczka> paczki = mapper.readValue(file, new TypeReference<List<Paczka>>() {});
            for (Paczka p : paczki) {
                if (p.getNumerPaczki() == numerPaczki) {
                    return p;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}