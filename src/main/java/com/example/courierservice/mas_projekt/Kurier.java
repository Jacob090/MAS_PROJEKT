package com.example.courierservice.mas_projekt;

public class Kurier extends Uzytkownik {
    private String miasto;
    private String pojazdId;

    public Kurier(int id, String imie, String nazwisko, String miasto, String pojazdId) {
        super(id, imie, nazwisko);
        this.miasto = miasto;
        this.pojazdId = pojazdId;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getPojazdId() {
        return pojazdId;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setPojazdId(String pojazdId) {
        this.pojazdId = pojazdId;
    }
}