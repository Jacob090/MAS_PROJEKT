package com.example.courierservice.mas_projekt;

public class Administrator extends Uzytkownik {
    private String haslo;

    public Administrator(int id, String imie, String nazwisko, String haslo) {
        super(id, imie, nazwisko);
        this.haslo = haslo;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
