package com.poo2.poo2_l.controllers.services;

import com.poo2.poo2_l.controllers.db.DatabaseController;
import com.poo2.poo2_l.controllers.ui.IObserver;
import com.poo2.poo2_l.models.IEntidade;
import com.poo2.poo2_l.models.Projeto;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

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
        if (!query.isEmpty() && query.iterator().next() instanceof IEntidade) {
            _todos.clear();
            _todos.addAll(query);
        }
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
        _todos.add(e);
        DatabaseController.getDBControl().setEntidade(e);
        sinalizarObservers();
    }

    public void atualizar(Projeto p) {
        DatabaseController.getDBControl().updateEntidade(p);
        sinalizarObservers();
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
        sinalizarObservers();
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
