package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import com.example.courierservice.mas_projekt.Paczka;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EkranKlienta {
    @FXML
    private Button ekran_klienta_powrot;

    @FXML
    private Button ekran_klienta_ocen_dostawe;

    @FXML
    private Button ekran_klienta_zwrot_paczki;

    @FXML
    private Label ekran_klienta_status_label;

    public static Paczka paczka;

    public static void ustawPaczke(Paczka p) {
        paczka = p;
    }

    public static Paczka getPaczka() {
        return paczka;
    }


    public void initialize() {
        ekran_klienta_powrot.setOnAction(event -> powrot());
        ekran_klienta_ocen_dostawe.setOnAction(event -> ocenaDostawy());
        ekran_klienta_zwrot_paczki.setOnAction(event -> reklamacja());

        if (paczka != null) {
            ekran_klienta_status_label.setText(paczka.getStatus());
        }

        ekran_klienta_ocen_dostawe.setDisable(true);
        ekran_klienta_zwrot_paczki.setDisable(true);

        if (paczka != null && paczka.getStatus().equals("Dostarczona") && !czyOceniono) {
            ekran_klienta_ocen_dostawe.setDisable(false);
            ekran_klienta_zwrot_paczki.setDisable(false);
        }
    }

    private void powrot(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

    private static boolean czyOceniono = false;

    private void ocenaDostawy(){
        Main.switchScene("ekran-oceny-dostawy.fxml");
    }

    private void reklamacja(){
        Main.switchScene("ekran-reklamacji.fxml");
    }

}
