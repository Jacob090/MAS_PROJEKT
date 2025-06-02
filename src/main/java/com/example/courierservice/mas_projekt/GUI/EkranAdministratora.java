package com.example.courierservice.mas_projekt.GUI;

import com.example.courierservice.mas_projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class EkranAdministratora {
    @FXML
    private Button ekran_administratora_dodaj_paczke;

    @FXML
    private Button ekran_administratora_usun_paczke;

    @FXML
    private Button ekran_administratora_dodaj_kuriera;

    @FXML
    private Button ekran_administratora_usun_kuriera;

    @FXML
    private Button ekran_administratora_dodaj_pojazd;

    @FXML
    private Button ekran_administratora_usun_pojazd;

    @FXML
    private Button ekran_administratora_edytuj_dane_kuriera;

    @FXML
    private Button ekran_administratora_zresetuj_haslo_kuriera;

    @FXML
    private Button ekran_administratora_wyloguj;

    @FXML
    private Button ekran_administratora_generuj_raport;

    @FXML
    private ListView<String> ekran_administratora_lista_paczek;

    @FXML
    private ListView<String> ekran_administratora_lista_kurierów;

    @FXML
    private ListView<String> ekran_administratora_lista_pojazdow;

    public void initialize(){
        //ekran_administratora_dodaj_paczke.setOnAction(event -> X);
        //ekran_administratora_usun_paczke.setOnAction(event -> X);
        //ekran_administratora_dodaj_kuriera.setOnAction(event -> X);
        //ekran_administratora_usun_kuriera.setOnAction(event -> X);
        //ekran_administratora_dodaj_pojazd.setOnAction(event -> X);
        //ekran_administratora_usun_pojazd.setOnAction(event -> X);
        //ekran_administratora_edytuj_dane_kuriera.setOnAction(event -> X);
        //ekran_administratora_zresetuj_haslo_kuriera.setOnAction(event -> X);
        ekran_administratora_wyloguj.setOnAction(event -> wyloguj());
        //ekran_administratora_generuj_raport.setOnAction(event -> X);
        //ekran_administratora_lista_paczek.setOnAction(event -> X);
        //ekran_administratora_lista_kurierów.setOnAction(event -> X);
        //ekran_administratora_lista_pojazdow.setOnAction(event -> X);
    }

    /*
    @FXML
private ListView<Kurier> listaKurierow;

public void initialize() {
    ObjectMapper mapper = new ObjectMapper();
    try {
        List<Kurier> kurierzy = mapper.readValue(new File("dane/kurierzy.json"),
            new TypeReference<List<Kurier>>() {});
        ObservableList<Kurier> observableList = FXCollections.observableArrayList(kurierzy);
        listaKurierow.setItems(observableList);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
     */

    private void wyloguj(){
        Main.switchScene("ekran-glowny-logowania.fxml");
    }



}
