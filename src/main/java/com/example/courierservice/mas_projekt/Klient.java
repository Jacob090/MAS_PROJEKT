package com.example.courierservice.mas_projekt;

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
}