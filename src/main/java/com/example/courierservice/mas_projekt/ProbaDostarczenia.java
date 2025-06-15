package com.example.courierservice.mas_projekt;

import java.util.HashMap;
import java.util.Map;
import java.lang.System;


public class ProbaDostarczenia {

    private static final int MAKS_PROBY = 2;

    private static Map<Integer, Integer> probyDostarczenia = new HashMap<>();

    public static boolean zarejestrujProbe(int numerPaczki) {
        int aktualneProby = probyDostarczenia.getOrDefault(numerPaczki, 0);

        if (aktualneProby >= MAKS_PROBY) {
            System.out.println("Nie można zarejestrować kolejnej próby dla paczki nr " + numerPaczki +
                    ". Maksymalna liczba prób (" + MAKS_PROBY + ") została już wykorzystana.");
            return false;
        }

        probyDostarczenia.put(numerPaczki, aktualneProby + 1);
        System.out.println("Zarejestrowano próbę dostarczenia nr " + (aktualneProby + 1) +
                " dla paczki nr " + numerPaczki + ".");
        return true;
    }

    public static int getLiczbaProb(int numerPaczki) {
        return probyDostarczenia.getOrDefault(numerPaczki, 0);
    }
}
