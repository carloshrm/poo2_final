package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.DatabaseController;
import com.poo2.poo2_l.models.Tarefa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class GerenciadorDeTarefas extends Application {

    public static void main(String[] args) {
        var db = DatabaseController.getDBControl();
        var tarefaDemo1 = new Tarefa("abcd", new Date(), "Teste");
        var tarefaDemo2 = new Tarefa("1234", new Date(), "ZZZZ");
        db.setEntidade(tarefaDemo1);
        db.setEntidade(tarefaDemo2);
//        db.getTabela("Tarefa").forEach(t -> System.out.println(t));
//        tarefaDemo1.setTitulo("00000");
//        db.updateTarefa(tarefaDemo1);
//        db.getTabela("Tarefa").forEach(t -> System.out.println(t));
        db.removeEntidade(tarefaDemo1);
        db.removeEntidade(tarefaDemo2);
        System.out.println("removidas");
        db.getTabela("Tarefa").forEach(t -> System.out.println(t));
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GerenciadorDeTarefas.class.getResource("GerenciadorDeTarefas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}