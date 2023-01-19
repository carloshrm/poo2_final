package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.DatabaseController;
import com.poo2.poo2_l.controllers.ui.GerenciadorDeTarefasController;
import com.poo2.poo2_l.models.Tarefa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class GerenciadorDeTarefas extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GerenciadorDeTarefas.class.getResource("GerenciadorDeTarefas.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        GerenciadorDeTarefasController ctrl = fxmlLoader.getController();
        ctrl.setTarefaServ(new TarefaService());
        stage.show();
    }
}