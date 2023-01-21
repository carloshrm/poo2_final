package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.IComando;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.concurrent.Callable;

public class AvisoUI {
    @FXML
    private Label avisoLabel;
    @FXML
    private Button avisoOK;
    @FXML
    private Button avisoCCL;

    private IComando act;

    public void setFuncao(IComando c) {
        act = c;
    }

    public void setTexto(String t) {
        avisoLabel.setText(t);
    }

    @FXML
    private void onOK() {
        try {
            act.executar();
            ((Stage) avisoOK.getScene().getWindow()).close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onCCL() {
        ((Stage) avisoCCL.getScene().getWindow()).close();
    }
}
