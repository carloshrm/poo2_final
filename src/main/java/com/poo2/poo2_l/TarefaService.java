package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.DatabaseController;
import com.poo2.poo2_l.models.Tarefa;

import java.util.HashSet;
import java.util.Set;


/**
 * Classe que agrega e organiza informações sobre o conjunto de dados das tarefas, e as expoe de forma organizada.
 * Esta classe procura implementar o padrão facade, garantindo o comportamento correto da lógica do programa em relação
 * ao banco de dados, simplificando quaisquer operações sobre os dados do programa.
 */
public class TarefaService extends Service<Tarefa> {
    private Set<Tarefa> _todas;

    public TarefaService() {
        _todas = new HashSet<>();
        lerTarefas();
    }

    private void lerTarefas() {
        _todas.clear();
        _todas.addAll(DatabaseController.getDBControl().getTabela("Tarefa"));
    }

    public Set<Tarefa> getTodas() {
        lerTarefas();
        return _todas;
    }

    public void criar(Tarefa t) {
        _todas.add(t);
        DatabaseController.getDBControl().setEntidade(t);
    }
}
