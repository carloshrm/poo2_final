package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        exemplos();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("view/Main.css").toExternalForm());
        stage.setTitle("Gerenciador de Tarefas");
        stage.setScene(scene);
        stage.show();
    }

    public void exemplos() {
        var ctrl = new ProjetoService();
        var p = ctrl.getTudo();
        if (p == null || p.isEmpty()) {
            ctrl.criar(new Projeto("Exemplo 1", "Exemplo de projeto", new Date()));
        }
        var ctrl2 = new TarefaService();
        var t = ctrl2.getTudo();
        if (t == null || t.isEmpty()) {
            ctrl2.criar(new Tarefa("Titulo 1", new Date(), "Exemplo 1"));
            ctrl2.criar(new Tarefa("Titulo 2", new Date(), "Exemplo 2"));
            var t3 = new Tarefa("Titulo 3", new Date(), "Exemplo 3");
            t3.setProjeto(ctrl.getTudo().iterator().next());
            ctrl2.criar(t3);
        }

    }
}