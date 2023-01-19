package com.poo2.poo2_l;

import com.poo2.poo2_l.models.IEntidade;
import com.poo2.poo2_l.models.Tarefa;

import java.util.Set;

public abstract class Service<T extends IEntidade> {
    abstract Set<T> getTodas();
    abstract void criar(T e);
}
