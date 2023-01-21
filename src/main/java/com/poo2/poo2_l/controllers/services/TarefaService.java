package com.poo2.poo2_l.controllers.services;

import com.poo2.poo2_l.controllers.db.DatabaseController;
import com.poo2.poo2_l.models.IEntidade;
import com.poo2.poo2_l.models.Projeto;
import com.poo2.poo2_l.models.Tarefa;

import java.util.HashSet;
import java.util.Set;


/**
 * Classe que agrega e organiza informações sobre o conjunto de dados das tarefas, e as expoe de forma organizada.
 * Esta classe procura implementar o padrão facade, garantindo o comportamento correto da lógica do programa em relação
 * ao banco de dados, simplificando quaisquer operações sobre os dados do programa.
 */
public class TarefaService implements Service<Tarefa> {
    private static TarefaService _tarefaService;
    private final Set<Tarefa> _todas;

    private TarefaService() {
        _todas = new HashSet<>();
        lerTarefas();
    }

    public static TarefaService getInstance() {
        if (_tarefaService == null)
            _tarefaService = new TarefaService();
        return _tarefaService;
    }

    private void lerTarefas() {
        var query = DatabaseController.getDBControl().getTabela(Tarefa.class);
        if (!query.isEmpty() && query.iterator().next() instanceof IEntidade) {
            _todas.clear();
            _todas.addAll(query);
        }
    }

    public Set<Tarefa> getPorProjeto(Projeto p) {
        lerTarefas();
        return new HashSet<>(_todas.stream().filter(t -> {
            var prj = t.getProjeto();
            if (prj != null)
                return prj.equals(p);
            else
                return p == null;
        }).toList());
    }

    @Override
    public Tarefa getPorID(Long id) {
        return DatabaseController.getDBControl().getEntidadePorID(id, Tarefa.class);
    }

    @Override
    public Set<Tarefa> getTudo() {
        lerTarefas();
        return _todas;
    }

    @Override
    public void criar(Tarefa t) {
        _todas.add(t);
        DatabaseController.getDBControl().setEntidade(t);
    }

    public void atualizar(Tarefa t) {
        DatabaseController.getDBControl().updateEntidade(t);
        lerTarefas();
    }
}
