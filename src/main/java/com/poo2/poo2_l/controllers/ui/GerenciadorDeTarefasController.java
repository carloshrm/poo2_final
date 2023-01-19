package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.TarefaService;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;
import java.util.List;

public class GerenciadorDeTarefasController {
    @FXML
    private Label welcomeText;
    private TarefaService _tarefaServ;

    public void setTarefaServ(TarefaService _tarefaServ) {
        this._tarefaServ = _tarefaServ;
        System.out.println("serv set");
    }

    @FXML
    protected void onHelloButtonClick() {
        _tarefaServ.adicionarTarefa(new Tarefa("Exemplo 1", new Date(), "exemplo"));
        StringBuilder test = new StringBuilder();
        _tarefaServ.getTodasTarefas().forEach(t -> test.append(t.toString()));
        welcomeText.setText(test.toString());
    }
}