package com.example.courierservice.mas_projekt;

public class Paczka {
    private int numerPaczki;
    private String imieOdbiorcy;
    private String nazwiskoOdbiorcy;
    private String miastoDostawy;
    private String adresDostawy;
    private String status; // "Przyjęta", "Dostarczona", "W dostarczeniu", "Zwrot"
    private String typ;  //"Zwykła", "Priorytetowa"
    private String notatka;

    public Paczka() {} // wymagany przez Jackson

    public Paczka(int numerPaczki, String imieOdbiorcy, String nazwiskoOdbiorcy, String miastoDostawy, String adresDostawy, String status, String typ, String notatka) {
        this.numerPaczki = numerPaczki;
        this.imieOdbiorcy = imieOdbiorcy;
        this.nazwiskoOdbiorcy = nazwiskoOdbiorcy;
        this.miastoDostawy = miastoDostawy;
        this.adresDostawy = adresDostawy;
        this.status = status;
        this.typ = typ;
        this.notatka = notatka;
    }

    public int getNumerPaczki() {
        return numerPaczki;
    }

    public void setNumerPaczki(int numerPaczki) {
        this.numerPaczki = numerPaczki;
    }

    public String getImieOdbiorcy() {
        return imieOdbiorcy;
    }

    public void setImieOdbiorcy(String imieOdbiorcy) {
        this.imieOdbiorcy = imieOdbiorcy;
    }

    public String getNazwiskoOdbiorcy() {
        return nazwiskoOdbiorcy;
    }

    public void setNazwiskoOdbiorcy(String nazwiskoOdbiorcy) {
        this.nazwiskoOdbiorcy = nazwiskoOdbiorcy;
    }

    public String getMiastoDostawy() {
        return miastoDostawy;
    }

    public void setMiastoDostawy(String miastoDostawy) {
        this.miastoDostawy = miastoDostawy;
    }

    public String getAdresDostawy() {
        return adresDostawy;
    }

    public void setAdresDostawy(String adresDostawy) {
        this.adresDostawy = adresDostawy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }
}