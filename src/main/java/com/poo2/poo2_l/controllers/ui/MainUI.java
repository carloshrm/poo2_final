package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.Main;
import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainUI implements IObserver {
    @FXML
    private MenuItem menuNovoProjeto;
    @FXML
    private TabPane painelPrincipal;
    private TarefaService tarSvc;
    private ProjetoService projSvc;

    @FXML
    public void initialize() {
        setServices();
        setTabs();
    }

    private void setServices() {
        this.tarSvc = TarefaService.getInstance();
        this.projSvc = ProjetoService.getInstance();
        projSvc.registrarObserver(this);
    }

    private void setTabs() {
        Tab tabPrincipal = painelPrincipal.getTabs().get(0);
        tabPrincipal.setContent(configurarTab(null));
        painelPrincipal.getTabs().clear();
        var projetosDoUsuario = projSvc.getTudo().stream().map(p -> {
            var t = new Tab(p.getTitulo());
            t.setUserData(p);
            t.setId(p.getId().toString());
            return t;
        }).toList();
        painelPrincipal.getTabs().add(tabPrincipal);
        painelPrincipal.getTabs().addAll(projetosDoUsuario);
        painelPrincipal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> evento, Tab tabAnt, Tab tabClicada) {
                var projeto = (Projeto) tabClicada.getUserData();
                tabClicada.setContent(configurarTab(projeto));
            }
        });
    }

    private Node configurarTab(Projeto p) {
        var loader = new FXMLLoader(Main.class.getResource("view/Projeto.fxml"));
        Node n = null;
        try {
            n = loader.load();
            ProjetoUI ctrl = loader.getController();
            ctrl.setInfo(p, tarSvc.getPorProjeto(p));
            loader.setController(ctrl);
            return n;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }

    @FXML
    private void onNovoProjeto() {
        ViewService.getInstance().getProjetoForm(null).show();
    }

    @Override
    public void update() {
        setTabs();
    }
}