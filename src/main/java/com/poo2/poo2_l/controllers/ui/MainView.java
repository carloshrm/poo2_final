package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.Main;
import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

public class MainView implements IObserver {
    @FXML
    private MenuItem menuNovoProjeto;
    @FXML
    private TabPane painelPrincipal;
    private TarefaService tarSvc;
    private ProjetoService projSvc;

    @FXML
    public void initialize() {
        setServices();
        configurarTabs();
    }

    private void setServices() {
        this.tarSvc = TarefaService.getInstance();
        this.projSvc = ProjetoService.getInstance();
        projSvc.registrarObserver(this);
    }

    private void configurarTabs() {
        painelPrincipal.getTabs().get(0).setContent(ViewService.getInstance().getProjetoView(null));
        painelPrincipal.getTabs().addAll(projSvc.getTudo().stream().map(this::fazerTab).toList());
        painelPrincipal.getSelectionModel().selectedItemProperty().addListener((evento, tabAnt, tabClicada) -> {
            if (tabClicada != null) {
                var projeto = (Projeto) tabClicada.getUserData();
                tabClicada.setContent(ViewService.getInstance().getProjetoView(projeto));
            }
        });
    }

    private Tab fazerTab(Projeto p) {
        var t = new Tab(p.getTitulo());
        t.setUserData(p);
        t.setId(p.getId().toString());
        return t;
    }

    private void atualizarTabs() {
        var tabAtiva = painelPrincipal.getSelectionModel().getSelectedItem();
        var projetosDB = projSvc.getTudo();
        var removidos = new ArrayList<Tab>();
        painelPrincipal.getTabs().forEach(tab -> {
            var projTab = (Projeto) tab.getUserData();
            if (projTab != null) {
                if (projetosDB.contains(projTab)) {
                    tab.setText(projTab.getTitulo());
                    projetosDB.remove(projTab);
                } else {
                    removidos.add(tab);
                }
            }
        });
        painelPrincipal.getTabs().removeAll(removidos);
        projetosDB.forEach(projDB -> painelPrincipal.getTabs().add(fazerTab(projDB)));
        painelPrincipal.getSelectionModel().clearSelection();
        if (!removidos.contains(tabAtiva)) {
            painelPrincipal.getSelectionModel().select(tabAtiva);
        }
    }

    @FXML
    private void onNovoProjeto() {
        ViewService.getInstance().getProjetoForm(null).show();
    }

    @Override
    public void update() {
        atualizarTabs();
    }
}