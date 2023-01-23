package com.poo2.poo2_l.Interfaces;

public interface IObservable {

    void sinalizarObservers();

    void registrarObserver(IObserver o);

    void removerObserver(IObserver o);
}
