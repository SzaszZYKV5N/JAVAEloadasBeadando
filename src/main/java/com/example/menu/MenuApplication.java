package com.example.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class MenuApplication extends Application {

    public String oldal = "menu.fxml";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource(oldal));
        Scene scene = new Scene(fxmlLoader.load(), 800, 550);
        stage.setTitle("JAVA elméleti beadandó feladat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {



        launch();


    }
}