package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.IComando;
import com.poo2.poo2_l.Main;
import com.poo2.poo2_l.controllers.services.Service;
import com.poo2.poo2_l.models.Projeto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.Callable;

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

    public Stage getProjetoForm(Projeto p) {
        try {
            var fxmlLoader = new FXMLLoader(Main.class.getResource("view/ProjetoForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            ProjetoFormUI ctrl = fxmlLoader.getController();
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
            AvisoUI ctrl = fxmlLoader.getController();
            ctrl.setFuncao(c);
            ctrl.setTexto(conteudo);
            stage.setTitle("Aviso");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            return stage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }
}
