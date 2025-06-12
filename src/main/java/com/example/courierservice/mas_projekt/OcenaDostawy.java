package com.example.courierservice.mas_projekt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OcenaDostawy {
    private String ocenaDostawyPaczki;

    public OcenaDostawy(String ocenaDostawyPaczki) {
        this.ocenaDostawyPaczki = ocenaDostawyPaczki;
    }
}