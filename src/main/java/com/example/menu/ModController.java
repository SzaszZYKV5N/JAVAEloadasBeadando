package com.example.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ModController {

    @FXML
    public ComboBox<Integer> cb1; // A ComboBox most már Integer típusú
    @FXML
    public TextField ujSzerelo;
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

    private final String DB_URL = "jdbc:sqlite:/c:/adatbazis/javabead.db"; // Adatbázis URL

    @FXML
    public void initialize() throws SQLException {
        // Táblázat oszlopok inicializálása
        tablazat.getColumns().clear();
        idColumn = new TableColumn<>("Id");
        szereloColumn = new TableColumn<>("Szerelő");
        kezdevColumn = new TableColumn<>("Belépés éve");

        tablazat.getColumns().addAll(idColumn, szereloColumn, kezdevColumn);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        szereloColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        kezdevColumn.setCellValueFactory(new PropertyValueFactory<>("kezdev"));

        Tablabovit(); // Adatok betöltése
        Legordulo(); // Kombóbox töltése

        // ComboBox változás figyelése
        cb1.setOnAction(e -> {
            try {
                adatBetoltes();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void Legordulo() throws SQLException {
        cb1.getItems().clear();
        cb1.getItems().add(0); // Alapértelmezett elem
        String query = "SELECT az FROM szerelo";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                cb1.getItems().add(rs.getInt("az"));
            }
            cb1.getSelectionModel().selectFirst(); // Első elem kiválasztása
        }
    }

    private void adatBetoltes() throws SQLException {
        int kivalasztottId = cb1.getSelectionModel().getSelectedItem();
        if (kivalasztottId == 0) {
            ujSzerelo.clear();
            belepesEve.clear();
            return;
        }

        String query = "SELECT nev, kezdev FROM szerelo WHERE az = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, kivalasztottId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ujSzerelo.setText(rs.getString("nev"));
                    belepesEve.setText(String.valueOf(rs.getInt("kezdev")));
                }
            }
        }
    }

    public void modosit() throws SQLException {
        int kivalasztottId = cb1.getSelectionModel().getSelectedItem();
        String szereloNev = ujSzerelo.getText();
        int belepesEv;

        try {
            belepesEv = Integer.parseInt(belepesEve.getText());
        } catch (NumberFormatException e) {
            System.out.println("Hibás év formátum!");
            return;
        }

        if (kivalasztottId != 0 && !szereloNev.isEmpty() && belepesEv > 0) {
            String query = "UPDATE szerelo SET nev = ?, kezdev = ? WHERE az = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, szereloNev);
                pstmt.setInt(2, belepesEv);
                pstmt.setInt(3, kivalasztottId);
                pstmt.executeUpdate();

                // Frissítsd a táblázatot
                Tablabovit();
                // Töröld a mezőket
                ujSzerelo.clear();
                belepesEve.clear();
                cb1.getSelectionModel().selectFirst();
            }
        } else {
            System.out.println("Kérjük, töltse ki a mezőket megfelelően!");
        }
    }

    private void Tablabovit() throws SQLException {
        tablazat.getItems().clear(); // Táblázat elemeinek törlése
        String query = "SELECT az, nev, kezdev FROM szerelo ORDER BY az";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                tablazat.getItems().add(new Szerelo(rs.getInt("az"), rs.getString("nev"), rs.getInt("kezdev")));
            }
        }
    }
}