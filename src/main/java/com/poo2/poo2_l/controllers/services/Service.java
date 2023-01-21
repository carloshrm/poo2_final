package com.poo2.poo2_l.controllers.services;

import com.poo2.poo2_l.models.IEntidade;

import java.util.Set;

public interface Service<T extends IEntidade> {
    Set<T> getTudo();
    T getPorID(Long id);
    void criar(T e);
}
