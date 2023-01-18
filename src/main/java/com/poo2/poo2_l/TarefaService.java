package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.DatabaseController;
import com.poo2.poo2_l.models.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private List<Tarefa> todasTarefas;

    public TarefaService() {
        todasTarefas = new ArrayList<>();
        todasTarefas.addAll(DatabaseController.getDBControl().getTabela("Tarefa"));
    }

    public List<Tarefa> getTarefasDB() {
        return todasTarefas;
    }

    
}
