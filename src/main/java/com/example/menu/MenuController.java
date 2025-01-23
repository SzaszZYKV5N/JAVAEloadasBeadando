package com.example.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    AnchorPane rootPane;
    /*@FXML
    private TableView<OlvasController.TableRow> tableView;
    */
    @FXML
    private void Ujablak(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("second.fxml"));
        rootPane.getChildren().setAll(pane);


    }
    private void loadFXMLToRootPane(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent newContent = loader.load();
            rootPane.getChildren().setAll(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleMenuAction(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        String fxmlFileName = "";

        switch (source.getText()) {
            case "Olvas":
                fxmlFileName = "olvas.fxml";
                break;
            case "Olvas2":
                fxmlFileName = "olvas2.fxml";
                break;
            case "Ír":
                fxmlFileName = "ir.fxml";
                break;
            case "Módosít":
                fxmlFileName = "modosit.fxml";
                break;
            case "Töröl":
                fxmlFileName = "torol.fxml";
                break;
            case "Párhuzamos":
                fxmlFileName = "parh.fxml";
                break;

            // További esetek hozzáadása szükség szerint
            default:
                System.out.println("Ismeretlen menüpont: " + source.getText());
                return;
        }

        loadFXMLToRootPane(fxmlFileName);
    }

}