package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.Main;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Set;

public class ProjetoControlUI extends FlowPane {

    @FXML
    private Label projetoTituloLabel;
    @FXML
    private Label projetoDescLabel;
    @FXML
    private Pane tarefasPane;

    private Projeto _projeto;
    private Set<Tarefa> _tarefas;

    public void setInfo(Projeto p, Set<Tarefa> t) {
        _projeto = p;
        _tarefas = t;
        if (_projeto != null) {
            projetoTituloLabel.setText(_projeto.getTitulo());
            projetoDescLabel.setText(_projeto.getDescricao());
        } else {
            projetoTituloLabel.setText("Tarefas");
            projetoDescLabel.setText("Tarefas sem um projeto definido.");
        }
        var info = _tarefas.stream().map(tr -> new Label(tr.toString())).toList();
        tarefasPane.getChildren().addAll(info);
    }
}
