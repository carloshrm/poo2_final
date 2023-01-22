package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class TarefaView extends TitledPane {
    @FXML
    private TitledPane tarefaPrincipal;
    @FXML
    private Label tarefaDesc;
    @FXML
    private DatePicker dataCriada;
    @FXML
    private DatePicker dataLimite;
    @FXML
    private Button editarTarefa;
    @FXML
    private Button removerTarefa;

    public void setInfo(Tarefa t) {
        tarefaPrincipal.setText(t.getTitulo());
        tarefaDesc.setText(t.getDescricao());
        dataCriada.setValue(t.getDataCriada());
        dataLimite.setValue(t.getDataLimite());
    }

    @FXML
    private void onEditarTarefa() {
        System.out.println("eitar tarefa");
    }

    @FXML
    private void onRemoverTarefa() {
        System.out.println("Remover tarefa");
    }
}
