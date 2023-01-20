package com.poo2.poo2_l.controllers.services;

import com.poo2.poo2_l.models.IEntidade;

import java.util.Set;

public abstract class Service<T extends IEntidade> {
    abstract Set<T> getTudo();
    abstract T getPorID(Long id);
    abstract void criar(T e);
}
