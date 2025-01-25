package com.example.menu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParhController {
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    private ScheduledExecutorService futtato;

    @FXML
    private void elindit() {
        futtato = Executors.newScheduledThreadPool(2);

        futtato.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> label1.setText("Label 1: " + System.currentTimeMillis()));
        }, 0, 1, TimeUnit.SECONDS);

        futtato.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> label2.setText("Label 2: " + System.currentTimeMillis()));
        }, 0, 2, TimeUnit.SECONDS);
    }

    public void leallit() {
        if (futtato != null) {
            futtato.shutdown();
        }
    }


}
