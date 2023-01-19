package com.poo2.poo2_l;

import com.poo2.poo2_l.controllers.DatabaseController;
import com.poo2.poo2_l.models.Projeto;

import java.util.HashSet;
import java.util.Set;

public class ProjetoService extends Service<Projeto> {

    private Set<Projeto> _todos;

    public ProjetoService() {
        this._todos = new HashSet<>();
        lerProjetos();
    }

    private void lerProjetos() {
        _todos.clear();
        _todos.addAll(DatabaseController.getDBControl().getTabela("Projeto"));
    }

    @Override
    public Set<Projeto> getTodas() {
        return _todos;
    }

    @Override
    public void criar(Projeto e) {
        _todos.add(e);
        DatabaseController.getDBControl().setEntidade(e);
    }
}
