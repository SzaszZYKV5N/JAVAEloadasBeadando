package com.example.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class TorolController {

    @FXML
    ComboBox<Integer> cb1; // A ComboBox most már Integer típusú
    @FXML
    TextField belepesEve;
    @FXML
    public TableView<Szerelo> tablazat;
    @FXML
    private TableColumn<Szerelo, Integer> idColumn;
    @FXML
    private TableColumn<Szerelo, String> szereloColumn;
    @FXML
    private TableColumn<Szerelo, String> kezdevColumn;

    @FXML
    public void initialize() throws SQLException {
        // Táblázat oszlopok konfigurálása
        tablazat.getColumns().clear();
        idColumn = new TableColumn<>("Id");
        szereloColumn = new TableColumn<>("Szerelő");
        kezdevColumn = new TableColumn<>("Belépés éve");

        tablazat.getColumns().addAll(idColumn, szereloColumn, kezdevColumn);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        szereloColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        kezdevColumn.setCellValueFactory(new PropertyValueFactory<>("kezdev"));

        // Adatok betöltése adatbázisból
        Tablabovit();
        Legordulo();
    }

    private void Tablabovit() throws SQLException {
        tablazat.getItems().clear(); // Táblázat elemeinek törlése
        Szerelo ujsor;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT az, nev, kezdev " +
                        "FROM szerelo  " +
                        "ORDER BY az"
        );
        while (rs.next()) {
            ujsor = new Szerelo(rs.getInt("az"), rs.getString("nev"), rs.getInt("kezdev"));
            tablazat.getItems().add(ujsor);
        }
    }

    private void Legordulo() throws SQLException {
        cb1.getItems().clear();
        cb1.getItems().add(0); // Alapértelmezett elem
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT az FROM szerelo");

        while (rs.next()) {
            int id = rs.getInt("az");
            cb1.getItems().add(id);
        }
        cb1.getSelectionModel().selectFirst(); // Első elem kiválasztása
    }

    public void torol(ActionEvent event) throws SQLException {
        int kivalasztottId = cb1.getSelectionModel().getSelectedItem();

        if (kivalasztottId != 0) { // Ellenőrizd, hogy nem az alapértelmezett elem van kiválasztva
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
            String query = "DELETE FROM szerelo WHERE az = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, kivalasztottId);
            pstmt.executeUpdate();

            // Rekord törlése után frissítsd a táblázatot és a ComboBox-ot
            Tablabovit();
            Legordulo();
        } else {
            // Hibaüzenet, ha az alapértelmezett elem van kiválasztva
            System.out.println("Kérjük, válasszon egy szerelőt a törléshez!");
        }
    }
}
