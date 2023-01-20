package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.Collection;
import java.util.List;

public class GerenciadorDeTarefasControllerUI {
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
        var projetos = _projetoService.getTudo().stream().map(p -> {
            var t = new Tab(p.getTitulo());
            t.setId(p.getId().toString());
            t.setUserData(p);
            return t;
        }).toList();
        painelPrincipal.getTabs().addAll(projetos);
        painelPrincipal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> evento, Tab tabAnt, Tab tabClicada) {
                System.out.println(tabClicada.getUserData());
                var ts = _tarefaService.getPorProjeto(((Projeto) tabClicada.getUserData()));
                var info = ts.stream().map(tr -> new Label(tr.toString())).toList();
                var fp = new FlowPane();
                fp.getChildren().addAll(info);
                tabClicada.setContent(fp);
            }
        });
    }

//    @FXML
//    protected void onHelloButtonClick() {
//        _tarefaService.criar(new Tarefa("", new Date(), ""));
//        StringBuilder test = new StringBuilder();
//        _tarefaService.getTodas().forEach(t -> test.append(t.toString() + "\n"));
//        welcomeText.setText(test.toString());
//    }
}