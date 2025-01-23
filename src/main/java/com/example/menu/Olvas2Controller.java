package com.example.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Olvas2Controller {

    @FXML
    private ComboBox<Szerelo> cb1;
@FXML private Label lbFeltet;
    @FXML
    public TableView<OlvasDAO> tablazat;
    @FXML
    private TableColumn<OlvasDAO, Integer> idColumn;
    @FXML
    private TableColumn<OlvasDAO, String> telepulesColumn;
    @FXML
    private TableColumn<OlvasDAO, String> utcaColumn;
    @FXML
    private TableColumn<OlvasDAO, String> szereloColumn;
    @FXML
    private TableColumn<OlvasDAO, String> oraColumn;
    @FXML
    private TableColumn<OlvasDAO, String> anyagarColumn;

    @FXML
    public void initialize() throws SQLException {



        // Táblázat oszlopok konfigurálása
        tablazat.getColumns().clear();
        idColumn = new TableColumn<>("Helyazonosító");
        telepulesColumn = new TableColumn<>("Település");
        utcaColumn = new TableColumn<>("Utca");
        szereloColumn = new TableColumn<>("Szerelő");
        oraColumn= new TableColumn<>("Munkaóra");
        anyagarColumn= new TableColumn<>("Anyagár");

        tablazat.getColumns().addAll(idColumn, telepulesColumn, utcaColumn, szereloColumn,oraColumn, anyagarColumn);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("helyaz"));
        telepulesColumn.setCellValueFactory(new PropertyValueFactory<>("telepules"));
        utcaColumn.setCellValueFactory(new PropertyValueFactory<>("utca"));
        szereloColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        oraColumn.setCellValueFactory(new PropertyValueFactory<>("munkaora"));
        anyagarColumn.setCellValueFactory(new PropertyValueFactory<>("anyagar"));
        //listView feltöltése
        Legordulo();
        feltetel = " WHERE sz.az = m.szereloaz AND m.helyaz = h.az ORDER BY m.helyaz";
        // Adatok betöltése adatbázisból
        Tablabovit();
    }



    private void Legordulo() throws SQLException {
        cb1.getItems().clear();
        cb1.getItems().add(new Szerelo(0, "Válassz")); // Alapértelmezett elem
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT az, nev FROM szerelo");

        while (rs.next()) {
            int id = rs.getInt("az");
            String nev = rs.getString("nev");
            cb1.getItems().add(new Szerelo(id, nev));
        }
        cb1.getSelectionModel().selectFirst(); // Első elem kiválasztása
    }

    public int kivSzer() {
        Szerelo kivalasztottSzerelo = cb1.getSelectionModel().getSelectedItem();
        lbFeltet.setText(kivalasztottSzerelo.getId()+"-"+kivalasztottSzerelo.getNev());
        return kivalasztottSzerelo.getId();
    }

    private String feltetel=" AND sz.az = m.szereloaz AND m.helyaz = h.az ORDER BY m.helyaz";

    public void Tablabovit() throws SQLException {
        tablazat.getItems().clear(); // Táblázat elemeinek törlése
        OlvasDAO ujsor;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT m.helyaz, h.telepules, h.utca, sz.nev, m.munkaora, m.anyagar " +
                        "FROM szerelo sz, munkalap m, hely h " +
                        feltetel
        );
        while (rs.next()) {
            ujsor = new OlvasDAO(rs.getInt("helyaz"), rs.getString("telepules"), rs.getString("utca"), rs.getString("nev"), rs.getInt("munkaora"), rs.getInt("anyagar"));
            tablazat.getItems().add(ujsor);
        }
    }


    public void szuresClick(ActionEvent event) throws SQLException {
        int a = kivSzer();
        if (a == 0) {
            feltetel = " WHERE sz.az = m.szereloaz AND m.helyaz = h.az ";
        } else {
            feltetel = " WHERE sz.az = m.szereloaz AND m.helyaz = h.az AND m.szereloaz = " + a;
        }
        feltetel += " ORDER BY m.helyaz";
        Tablabovit();
    }




}

