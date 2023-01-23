package com.poo2.poo2_l.controllers.view;

import com.poo2.poo2_l.Interfaces.IComando;
import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;

import java.time.LocalDate;

public class TarefaView extends TitledPane {
    @FXML
    private TitledPane tarefaPrincipal;
    @FXML
    private Label tarefaDesc;
    @FXML
    private Label dataCriada;
    @FXML
    private Label dataLimite;
    @FXML
    private Button editarTarefa;
    @FXML
    private Button removerTarefa;

    private Tarefa _tarefa;

    public void setup(Tarefa t) {
        _tarefa = t;
        tarefaPrincipal.setText(_tarefa.getTitulo());
        tarefaDesc.setText(_tarefa.getDescricao());
        dataCriada.setText(_tarefa.getDataCriada().toString());
        dataLimite.setText(_tarefa.getDataLimite().toString());
        tarefaPrincipal.getStyleClass().add(getClasseCSSEstiloTitulo());
    }

    private String getClasseCSSEstiloTitulo() {
        var tempoAteLimite = LocalDate.now().until(_tarefa.getDataLimite());
        if (tempoAteLimite.isNegative() || tempoAteLimite.isZero())
            return "pronta";
        else {
            if (tempoAteLimite.getMonths() > 0) {
                return "baixa";
            } else
                return tempoAteLimite.getDays() >= 5 ? "media" : "alta";
        }
    }

    @FXML
    private void onEditarTarefa() {
        ViewService.getInstance().getTarefaForm(_tarefa, _tarefa.getProjeto()).show();
    }

    @FXML
    private void onRemoverTarefa() {
        var comando = new IComando() {
            @Override
            public void executar() {
                TarefaService.getInstance().remover(_tarefa);
            }
        };
        ViewService.getInstance().getAviso(comando, "Tem certeza que deseja remover essa tarefa: " + _tarefa.getTitulo() + "?").show();
    }
}
