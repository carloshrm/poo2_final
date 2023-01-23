package com.poo2.poo2_l.controllers.services;

import com.poo2.poo2_l.controllers.db.DatabaseController;
import com.poo2.poo2_l.controllers.view.IObserver;
import com.poo2.poo2_l.Interfaces.IEntidade;
import com.poo2.poo2_l.models.Projeto;

import java.util.*;

public class ProjetoService implements Service<Projeto> {

    private static ProjetoService _projetoService;
    private final Set<Projeto> _todos;
    private List<IObserver> observers;

    private ProjetoService() {
        this._todos = new HashSet<>();
        observers = new ArrayList<>();
        lerProjetos();
    }

    public static ProjetoService getInstance() {
        if (_projetoService == null) _projetoService = new ProjetoService();
        return _projetoService;
    }

    private void lerProjetos() {
        var query = DatabaseController.getDBControl().getTabela(Projeto.class);
        _todos.clear();
        if (!query.isEmpty() && query.iterator().next() instanceof IEntidade) {
            _todos.addAll(query);
        }
        sinalizarObservers();
    }

    @Override
    public Projeto getPorID(Long id) {
        return DatabaseController.getDBControl().getEntidadePorID(id, Projeto.class);
    }

    @Override
    public Set<Projeto> getTudo() {
        return _todos;
    }

    @Override
    public void criar(Projeto e) {
        DatabaseController.getDBControl().setEntidade(e);
        lerProjetos();
    }

    public void atualizar(Projeto p) {
        DatabaseController.getDBControl().updateEntidade(p);
        lerProjetos();
    }

    public void remover(Projeto p) {
        var tserv = TarefaService.getInstance();
        var tarefas = tserv.getPorProjeto(p);
        tarefas.forEach(t -> {
            t.setProjeto(null);
            tserv.atualizar(t);
        });
        DatabaseController.getDBControl().removeEntidade(p);
        lerProjetos();
    }

    private void sinalizarObservers() {
        observers.forEach(o -> o.update());
    }

    public void registrarObserver(IObserver o) {
        observers.add(o);
    }

    public void removerObserver(IObserver o) {
        observers.remove(o);
    }
}
