package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.Main;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.Set;

public class ProjetoControlUI extends FlowPane {

    @FXML
    private Label projetoTituloLabel;
    @FXML
    private Label projetoDescLabel;

    private Projeto _projeto;
    private Set<Tarefa> _tarefas;

    public ProjetoControlUI(Projeto p, Set<Tarefa> t) {
        _projeto = p;
        _tarefas = t;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Projeto.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize() {
        if (_projeto != null) {
            projetoTituloLabel.setText(_projeto.getTitulo());
            projetoDescLabel.setText(_projeto.getDescricao());
        }
        var info = _tarefas.stream().map(tr -> new Label(tr.toString())).toList();
        this.getChildren().addAll(info);
    }
}
