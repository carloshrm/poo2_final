package com.poo2.poo2_l.controllers.view;

public interface IObservable {

    void sinalizarObservers();

    void registrarObserver(IObserver o);

    void removerObserver(IObserver o);
}
