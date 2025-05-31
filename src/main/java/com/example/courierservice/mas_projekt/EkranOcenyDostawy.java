package com.example.courierservice.mas_projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EkranOcenyDostawy {

    @FXML
    private Button ekran_oceny_dostawy_powrot;

    @FXML
    private Button ekran_oceny_dostawy_ocena1;

    @FXML
    private Button ekran_oceny_dostawy_ocena2;

    @FXML
    private Button ekran_oceny_dostawy_ocena3;

    @FXML
    private Button ekran_oceny_dostawy_ocena4;

    @FXML
    private Button ekran_oceny_dostawy_ocena5;

    @FXML
    private Button ekran_oceny_zatwierdzanie;

    @FXML
    private TextArea ekran_oceny_komentarz;


    public void initialize() {
        ekran_oceny_dostawy_powrot.setOnAction(action -> powrot());
        ekran_oceny_dostawy_ocena1.setOnAction();
        ekran_oceny_dostawy_ocena2.setOnAction();
        ekran_oceny_dostawy_ocena3.setOnAction();
        ekran_oceny_dostawy_ocena4.setOnAction();
        ekran_oceny_dostawy_ocena5.setOnAction();
        ekran_oceny_zatwierdzanie.setOnAction();
    }

    private void powrot(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }

}
