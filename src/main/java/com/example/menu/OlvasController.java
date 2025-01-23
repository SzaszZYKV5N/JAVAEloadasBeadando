package com.example.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OlvasController {


    @FXML
    private Label welcomeText;
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

        // Adatok betöltése adatbázisból
        Tablabovit();
    }



    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Welcome to JavaFX Application!");
        OlvasDAO newOlvasDAO = new OlvasDAO( 0,"Budapest", "Podhorszky utca 68","Szabó Zotya",5,25000);
        tablazat.getItems().add(newOlvasDAO);




    }
    public   void  Tablabovit() throws SQLException {
        OlvasDAO newOlvasDAO = new OlvasDAO(1,"Budapest", "Podhorszky utca 68"," Kiss Lajos",3,15000);
        OlvasDAO ujsor;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(
                "SELECT m.helyaz, h.telepules, h.utca, sz.nev, m.munkaora, m.anyagar " +
                        "FROM szerelo sz, munkalap m, hely h " +
                        "WHERE sz.az = m.szereloaz AND m.helyaz = h.az"+" ORDER BY m.helyaz"
        );
        while(rs.next()){

            ujsor = new OlvasDAO(rs.getInt("helyaz"), rs.getString("telepules"), rs.getString("utca"),rs.getString("nev"), rs.getInt("munkaora"), rs.getInt("anyagar")   );
            tablazat.getItems().add(ujsor);

        }

        System.out.println("A tábla sorainak a száma:" + rs.getInt(1));
    }


}