package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.IComando;
import com.poo2.poo2_l.Main;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewService {
    private static ViewService _viewService;

    private ViewService() {
        //
    }

    public static ViewService getInstance() {
        if (_viewService == null)
            _viewService = new ViewService();
        return _viewService;
    }

    public Node getTarefaView(Tarefa t) {
        var loader = new FXMLLoader(Main.class.getResource("view/Tarefa.fxml"));
        try {
            Node n = loader.load();
            TarefaView ctrl = loader.getController();
            ctrl.setInfo(t);
            loader.setController(ctrl);
            return n;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }

    public Node getProjetoView(Projeto p) {
        var loader = new FXMLLoader(Main.class.getResource("view/Projeto.fxml"));
        try {
            Node n = loader.load();
            ProjetoView ctrl = loader.getController();
            ctrl.setInfo(p, TarefaService.getInstance().getPorProjeto(p));
            loader.setController(ctrl);
            return n;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }

    public Stage getProjetoForm(Projeto p) {
        try {
            var fxmlLoader = new FXMLLoader(Main.class.getResource("view/ProjetoForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            ProjetoFormView ctrl = fxmlLoader.getController();
            ctrl.setEdicao(p);
            stage.setTitle("Novo Projeto");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            return stage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }

    public Stage fazerAviso(IComando c, String conteudo) {
        try {
            var fxmlLoader = new FXMLLoader(Main.class.getResource("view/Aviso.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            AvisoView ctrl = fxmlLoader.getController();
            ctrl.setFuncao(c);
            ctrl.setTexto(conteudo);
            stage.setTitle("Aviso");
            stage.setScene(scene);
            return stage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }
}
