package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reklamacja {
    private String powodReklamacji;

    public Reklamacja(String powodReklamacji) {
        this.powodReklamacji = powodReklamacji;
    }

    private void zapiszDoPlikuRaportu(RaportDanych raport) {
        ObjectMapper mapper = new ObjectMapper();
        File plik = new File("daneDoRaportu.json");

        try {
            List<RaportDanych> dane;
            if (plik.exists()) {
                dane = mapper.readValue(plik, new TypeReference<>() {});
            } else {
                dane = new ArrayList<>();
            }

            dane.add(raport);
            mapper.writerWithDefaultPrettyPrinter().writeValue(plik, dane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}