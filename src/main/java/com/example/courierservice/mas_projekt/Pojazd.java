package com.example.courierservice.mas_projekt;

public class Pojazd {
    private String model;
    private String numerRejestracyjny;
    private int IDkuriera;

    public Pojazd() {}

    public Pojazd(String model, String numerRejestracyjny, int kurierId) {
        this.model = model;
        this.numerRejestracyjny = numerRejestracyjny;
        this.IDkuriera = kurierId;
    }

    public String getModel() {
        return model;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public int getIDkuriera() {
        return IDkuriera;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public void setIDkuriera(int IDkuriera) {
        this.IDkuriera = IDkuriera;

    }
}

