package com.poo2.poo2_l.controllers.view;

import com.poo2.poo2_l.Interfaces.IComando;
import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class ProjetoView extends FlowPane implements IObserver {
    @FXML
    private Label criadoTextoLabel;
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
    @FXML
    private Label dataInicio;

    private Projeto _projeto;

    public void setup(Projeto p) {
        _projeto = p;
        if (_projeto != null) {
            projetoTituloLabel.setText(_projeto.getTitulo());
            projetoDescLabel.setText(_projeto.getDescricao());
            dataInicio.setText(_projeto.getDataInicio().toString());
        } else {
            projetoTituloLabel.setText("Tarefas");
            projetoDescLabel.setText("Tarefas sem um projeto definido.");
            editarProjeto.setVisible(false);
            removerProjeto.setVisible(false);
            dataInicio.setVisible(false);
            criadoTextoLabel.setVisible(false);
        }
        TarefaService.getInstance().registrarObserver(this);
        popularTarefas();
    }

    private void popularTarefas() {
        tarefasPane.getChildren().clear();
        tarefasPane.getChildren().addAll(TarefaService.getInstance().getPorProjeto(_projeto).stream().map(trf -> ViewService.getInstance().getTarefaView(trf)).toList());
    }

    @FXML
    private void onAdicionarTarefa() {
        ViewService.getInstance().getTarefaForm(null, _projeto).show();
    }

    @FXML
    private void onEditarProjeto() {
        ViewService.getInstance().getProjetoForm(_projeto).show();
    }

    @FXML
    private void onRemoverProjeto() {
        var comando = new IComando() {
            @Override
            public void executar() {
                ProjetoService.getInstance().remover(_projeto);
            }
        };
        ViewService.getInstance().getAviso(comando, "Tem certeza que deseja remover esse projeto? As tarefas serão movidas para a seção sem projeto.").show();
    }

    public void onTroca() {
        TarefaService.getInstance().removerObserver(this);
    }

    @Override
    public void update() {
        popularTarefas();
    }
}
