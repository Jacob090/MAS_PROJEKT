package com.example.courierservice.mas_projekt;

public class Session {
    private static int zalogowanyKurierId = 0;
    private static int obecnaPaczkaId = 0;

    public static void setZalogowanyKurierId(int id) {
        zalogowanyKurierId = id;
    }

    public static int getZalogowanyKurierId() {
        return zalogowanyKurierId;
    }

    public static void setObecnaPaczkaId(int obecnaPaczkaId) {
        Session.obecnaPaczkaId = obecnaPaczkaId;
    }

    public static int getObecnaPaczkaId() {
        return obecnaPaczkaId;
    }
}