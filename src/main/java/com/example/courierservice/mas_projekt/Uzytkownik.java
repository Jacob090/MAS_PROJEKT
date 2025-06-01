package com.example.courierservice.mas_projekt;

public abstract class Uzytkownik {
    protected int id;
    protected String imie;
    protected String nazwisko;

    public Uzytkownik(int id, String imie, String nazwisko) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
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

