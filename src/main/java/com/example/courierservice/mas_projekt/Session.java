package com.example.courierservice.mas_projekt;

public class Session {
    private static int zalogowanyKurierId = 0;

    public static void setZalogowanyKurierId(int id) {
        zalogowanyKurierId = id;
    }

    public static int getZalogowanyKurierId() {
        return zalogowanyKurierId;
    }
}