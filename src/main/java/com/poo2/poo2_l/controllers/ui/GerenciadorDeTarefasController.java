package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.TarefaService;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;

public class GerenciadorDeTarefasController {
    @FXML
    private Label welcomeText;
    private TarefaService _tarefaService;

    public void setTarefaServ(TarefaService ts) {
        this._tarefaService = ts;
    }

    @FXML
    protected void onHelloButtonClick() {
        _tarefaService.criar(new Tarefa("", new Date(), ""));
        StringBuilder test = new StringBuilder();
        _tarefaService.getTodas().forEach(t -> test.append(t.toString() + "\n"));
        welcomeText.setText(test.toString());
    }
}