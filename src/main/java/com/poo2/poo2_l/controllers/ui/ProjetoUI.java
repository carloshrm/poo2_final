package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.Set;
import java.util.concurrent.Callable;

public class ProjetoUI extends FlowPane {

    @FXML
    private Label projetoTituloLabel;
    @FXML
    private Label projetoDescLabel;
    @FXML
    private Pane tarefasPane;
    @FXML
    private Button editarProjeto;
    @FXML
    private Button adicionarTarefa;
    @FXML
    private Button removerProjeto;

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
            editarProjeto.setDisable(true);
            removerProjeto.setDisable(true);
        }
        var info = _tarefas.stream().map(tr -> new Label(tr.toString())).toList();
        tarefasPane.getChildren().addAll(info);
    }

    @FXML
    private void onAdicionarTarefa() {
        System.out.println("add tar");
    }

    @FXML
    private void onEditarProjeto() {
        ViewService.getInstance().getProjetoForm(_projeto).show();
    }

    @FXML
    private void onRemoverProjeto() {
        ViewService.getInstance().fazerAviso(new Callable() {
            @Override
            public Object call() throws Exception {
                ProjetoService.getInstance().remover(_projeto);
                return null;
            }
        }, "Tem certeza que deseja remover esse projeto? As tarefas serão movidas para a seção sem projeto.").show();
    }

}
