package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import jakarta.persistence.TemporalType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        exemplos();
        System.setProperty("prism.lcdtext", "false");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("view/Main.css").toExternalForm());
        stage.setTitle("Gerenciador de Tarefas");
        stage.setScene(scene);
        stage.setOnCloseRequest((e) -> Platform.exit());
        stage.show();
    }
//
//    public void exemplos() {
//        var p = ProjetoService.getInstance().getTudo();
//        if (p == null || p.isEmpty()) {
//            ProjetoService.getInstance().criar(new Projeto("Tranquilo", "Projeto Tranquilo", LocalDate.now().minus(1, ChronoUnit.MONTHS)));
//            ProjetoService.getInstance().criar(new Projeto("Urgente", "Exemplo urgente", LocalDate.now()));
//        }
//        var tctrl = TarefaService.getInstance();
//        var t = tctrl.getTudo();
//        if (t == null || t.isEmpty()) {
//            var t1 = new Tarefa("Tarefa amanha", LocalDate.now().plus(1, ChronoUnit.DAYS), "Exemplo 1");
//            var t2 = new Tarefa("Tarefa semana que vem.", LocalDate.now().plus(6, ChronoUnit.DAYS), "Exemplo 2");
//            var t3 = new Tarefa("Tarefa mês que vem.", LocalDate.now().plus(1, ChronoUnit.MONTHS), "Exemplo 3");
//            tctrl.criar(t1);
//            tctrl.criar(t2);
//            tctrl.criar(t3);
//            var t4 = new Tarefa("Tarefa urgente", LocalDate.now().plus(2, ChronoUnit.DAYS), "Exemplo urgente");
//            var t5 = new Tarefa("Tarefa tranquila", LocalDate.now().plus(1, ChronoUnit.MONTHS), "Exemplo tranquilo");
//            var t6 = new Tarefa("Tarefa pronta", LocalDate.now().minus(1, ChronoUnit.MONTHS), "Exemplo pronto");
//            t4.setProjeto(ProjetoService.getInstance().getPorID(1L));
//            t5.setProjeto(ProjetoService.getInstance().getPorID(2L));
//            t6.setProjeto(ProjetoService.getInstance().getPorID(2L));
//            tctrl.criar(t4);
//            tctrl.criar(t5);
//            tctrl.criar(t6);
//        }
//    }
}