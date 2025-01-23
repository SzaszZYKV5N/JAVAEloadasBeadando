package com.example.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class IrController {
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
    }

    @FXML
    private Button btn1;

    private void Tablabovit() throws SQLException {

        tablazat.getItems().clear();
        Szerelo ujsor;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery(
                "SELECT az, nev, kezdev " +
                        "FROM szerelo  " +
                        "ORDER BY az"
        );
        while (rs.next()) {
            ujsor = new Szerelo(rs.getInt("az"), rs.getString("nev"), rs.getInt("kezdev"));
            tablazat.getItems().add(ujsor);

        }


    }

    public void felvesz(ActionEvent event) throws SQLException {
        String szereloNev = ujSzerelo.getText();
        int belepesEv = Integer.parseInt(belepesEve.getText());

        if (!szereloNev.isEmpty() && belepesEv > 0) {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/c:/adatbazis/javabead.db");
            String query = "INSERT INTO szerelo (nev, kezdev) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, szereloNev);
            pstmt.setInt(2, belepesEv);
            pstmt.executeUpdate();

            // Új rekord felvétele után frissítsd a táblázatot
            Tablabovit();

            // Mezők ürítése a következő bejegyzéshez
            ujSzerelo.clear();
            belepesEve.clear();
        } else {
            // Hibaüzenet, ha bármelyik mező üres vagy hibás
            System.out.println("Kérjük, töltse ki a mezőket megfelelően!");
        }
    }

}
