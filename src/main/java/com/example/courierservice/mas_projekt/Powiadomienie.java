package com.example.courierservice.mas_projekt;

import java.lang.System;


public class Powiadomienie {
    private String typ; // "email" / "SMS"
    private String tresc;

    public Powiadomienie(String typ, String tresc) {
        this.typ = typ;
        this.tresc = tresc;
    }

    public static void wyslijPowiadomienieODostarczeniu(int numerPaczki, String imieOdbiorcy, String nazwiskoOdbiorcy) {
        String wiadomosc = String.format("Powiadomienie: Paczka nr %d została dostarczona do %s %s.",
                numerPaczki, imieOdbiorcy, nazwiskoOdbiorcy);
        System.out.println(wiadomosc);
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
