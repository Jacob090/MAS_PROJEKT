package com.example.courierservice.mas_projekt;

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