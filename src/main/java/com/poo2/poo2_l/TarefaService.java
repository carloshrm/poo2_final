package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.DatabaseController;
import com.poo2.poo2_l.models.Tarefa;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que agrega e organiza informações sobre o conjunto de dados das tarefas, e as expoe de forma organizada.
 * Esta classe procura implementar o padrão facade, garantindo o comportamento correto da lógica do programa em relação
 * ao banco de dados, simplificando quaisquer operações sobre os dados do programa.
 */
public class TarefaService {
    private List<Tarefa> todasTarefas;

    public TarefaService() {
        todasTarefas = new ArrayList<>();
        lerTarefas();
    }

    private void lerTarefas() {
        todasTarefas.clear();
        todasTarefas.addAll(DatabaseController.getDBControl().getTabela("Tarefa"));
    }

    public List<Tarefa> getTodasTarefas() {
        return todasTarefas;
    }

    public void adicionarTarefa(Tarefa t) {
        todasTarefas.add(t);
        DatabaseController.getDBControl().setEntidade(t);
    }

}
