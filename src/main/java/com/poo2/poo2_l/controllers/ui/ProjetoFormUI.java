package com.poo2.poo2_l.controllers.ui;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.models.Projeto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Instant;
import java.util.Date;


public class ProjetoFormUI {
    @FXML
    private TextField fieldTitulo;
    @FXML
    private TextArea fieldDesc;
    @FXML
    private DatePicker fieldData;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button okButton;

    private Projeto projetoEditado;

    public void setEdicao(Projeto p) {
        projetoEditado = p;
        if (projetoEditado != null) {
            okButton.setText("Confirmar");
            fieldTitulo.setText(projetoEditado.getTitulo());
            fieldDesc.setText(projetoEditado.getDescricao());
            fieldData.setValue(projetoEditado.getDataInicio());
        }
    }

    @FXML
    private void onCancelarAction() {
        ((Stage) cancelarButton.getScene().getWindow()).close();
    }

    @FXML
    private void onOkAction() {
        if (projetoEditado != null) {
            projetoEditado.setTitulo(fieldTitulo.getText());
            projetoEditado.setDescricao(fieldDesc.getText());
            projetoEditado.setDataInicio(fieldData.getValue());
            ProjetoService.getInstance().atualizar(projetoEditado);
        } else {
            ProjetoService.getInstance().criar(new Projeto(fieldTitulo.getText(), fieldDesc.getText(), fieldData.getValue()));
        }
        ((Stage) okButton.getScene().getWindow()).close();
    }


}
