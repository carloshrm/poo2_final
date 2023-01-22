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
import java.time.LocalDate;
import java.util.Date;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        exemplos();
        System.setProperty("prism.lcdtext", "false");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("view/Main.css").toExternalForm());
        stage.setTitle("Gerenciador de Tarefas");
        stage.setScene(scene);
        stage.show();
    }

    public void exemplos() {

        var p = ProjetoService.getInstance().getTudo();
        if (p == null || p.isEmpty()) {
            ProjetoService.getInstance().criar(new Projeto("Exemplo 1", "Exemplo de projeto", LocalDate.now()));
        }

        var tctrl = TarefaService.getInstance();
        var t = tctrl.getTudo();
        if (t == null || t.isEmpty()) {
            tctrl.criar(new Tarefa("Titulo 1", LocalDate.now(), "Exemplo 1"));
            tctrl.criar(new Tarefa("Titulo 2", LocalDate.now(), "Exemplo 2"));
            var t3 = new Tarefa("Titulo 3", LocalDate.now(), "Exemplo 3");
            t3.setProjeto(ProjetoService.getInstance().getTudo().iterator().next());
            tctrl.criar(t3);
        }

    }
}