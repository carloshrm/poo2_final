package com.poo2.poo2_l.controllers.view;

import com.poo2.poo2_l.controllers.services.TarefaService;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TarefaFormView {
    @FXML
    private Label tarefaProjetoLabel;
    @FXML
    private TextField fieldTitulo;
    @FXML
    private TextArea fieldDesc;
    @FXML
    private DatePicker dataCriada;
    @FXML
    private DatePicker dataLimite;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button okButton;
    private Tarefa tarefaEdit;
    private Projeto projeto;

    public void setup(Tarefa t) {
        tarefaEdit = t;
        if (tarefaEdit != null) {
            setProjeto(projeto);
            okButton.setText("Confirmar");
            fieldTitulo.setText(tarefaEdit.getTitulo());
            fieldDesc.setText(tarefaEdit.getDescricao());
            dataCriada.setValue(tarefaEdit.getDataCriada());
            dataLimite.setValue(tarefaEdit.getDataLimite());
        }
    }

    public void setProjeto(Projeto p) {
        projeto = p;
        if (p != null)
            tarefaProjetoLabel.setText(projeto.getTitulo());
    }

    @FXML
    private void onOkAction() {
        if (tarefaEdit != null) {
            tarefaEdit.setTitulo(fieldTitulo.getText());
            tarefaEdit.setDescricao(fieldDesc.getText());
            tarefaEdit.setDataLimite(dataLimite.getValue());
            TarefaService.getInstance().atualizar(tarefaEdit);
        } else {
            var novaTarefa = new Tarefa(fieldTitulo.getText(), dataLimite.getValue(), fieldDesc.getText(), dataCriada.getValue());
            novaTarefa.setProjeto(projeto);
            TarefaService.getInstance().criar(novaTarefa);
        }
        ((Stage) okButton.getScene().getWindow()).close();
    }

    @FXML
    private void onCancelarAction() {
        ((Stage) cancelarButton.getScene().getWindow()).close();
    }
}
