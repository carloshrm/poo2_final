package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;

public class MainControllerUI {
    @FXML
    private TabPane painelPrincipal;
    private TarefaService _tarefaService;
    private ProjetoService _projetoService;

    @FXML
    public void initialize() {
        setServices();
        setTabs();
    }

    private void setServices() {
        this._tarefaService = new TarefaService();
        this._projetoService = new ProjetoService();
    }

    private void setTabs() {
        painelPrincipal.getTabs().get(0).setContent(preencherTabComTarefas(null));

        var projetosDoUsuario = _projetoService.getTudo().stream().map(p -> {
            var t = new Tab(p.getTitulo());
            t.setUserData(p);
            return t;
        }).toList();
        painelPrincipal.getTabs().addAll(projetosDoUsuario);
        painelPrincipal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> evento, Tab tabAnt, Tab tabClicada) {
                tabClicada.setContent(preencherTabComTarefas((Projeto) tabClicada.getUserData()));
            }
        });
    }

    private FlowPane preencherTabComTarefas(Projeto p) {
        var info = _tarefaService
                .getPorProjeto(p)
                .stream()
                .map(tr -> new Label(tr.toString())).toList();
        var painel = new FlowPane();
        painel.getChildren().addAll(info);
        return painel;
    }

//    @FXML
//    protected void onHelloButtonClick() {
//        _tarefaService.criar(new Tarefa("", new Date(), ""));
//        StringBuilder test = new StringBuilder();
//        _tarefaService.getTodas().forEach(t -> test.append(t.toString() + "\n"));
//        welcomeText.setText(test.toString());
//    }
}