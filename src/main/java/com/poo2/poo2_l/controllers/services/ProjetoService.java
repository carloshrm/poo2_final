package com.poo2.poo2_l.controllers.services;

import com.poo2.poo2_l.controllers.db.DatabaseController;
import com.poo2.poo2_l.models.IEntidade;
import com.poo2.poo2_l.models.Projeto;

import java.util.HashSet;
import java.util.Set;

public class ProjetoService extends Service<Projeto> {

    private final Set<Projeto> _todos;

    public ProjetoService() {
        this._todos = new HashSet<>();
        lerProjetos();
    }

    private void lerProjetos() {
        var query = DatabaseController.getDBControl().getTabela(Projeto.class);
        if (!query.isEmpty() && query.iterator().next() instanceof IEntidade) {
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
    }
}
