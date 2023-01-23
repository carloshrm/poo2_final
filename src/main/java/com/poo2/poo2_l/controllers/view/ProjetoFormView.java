package com.poo2.poo2_l.controllers.view;

import com.poo2.poo2_l.controllers.services.ProjetoService;
import com.poo2.poo2_l.models.Projeto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ProjetoFormView {
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

    private Projeto projetoEdit;

    public void setup(Projeto p) {
        projetoEdit = p;
        if (projetoEdit != null) {
            okButton.setText("Confirmar");
            fieldTitulo.setText(projetoEdit.getTitulo());
            fieldDesc.setText(projetoEdit.getDescricao());
            fieldData.setValue(projetoEdit.getDataInicio());
        }
    }

    @FXML
    private void onCancelarAction() {
        ((Stage) cancelarButton.getScene().getWindow()).close();
    }

    @FXML
    private void onOkAction() {
        if (projetoEdit != null) {
            projetoEdit.setTitulo(fieldTitulo.getText());
            projetoEdit.setDescricao(fieldDesc.getText());
            projetoEdit.setDataInicio(fieldData.getValue());
            ProjetoService.getInstance().atualizar(projetoEdit);
        } else {
            ProjetoService.getInstance().criar(new Projeto(fieldTitulo.getText(), fieldDesc.getText(), fieldData.getValue()));
        }
        ((Stage) okButton.getScene().getWindow()).close();
    }
}
