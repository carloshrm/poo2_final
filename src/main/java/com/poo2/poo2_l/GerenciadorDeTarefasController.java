package com.poo2.poo2_l;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GerenciadorDeTarefasController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}