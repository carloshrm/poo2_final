package com.poo2.poo2_l.controllers;

import java.util.*;

import com.poo2.poo2_l.models.Tarefa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseControllerTest {
    private static DatabaseController _dbc;
    private static List<Tarefa> exemplosUsados;

    @BeforeAll
    public static void setup() {
        _dbc = DatabaseController.getDBControl();
        exemplosUsados = new ArrayList<Tarefa>();
    }

    @AfterAll
    public static void limparExemplos() {
        for (Tarefa t : exemplosUsados) {
            _dbc.removeTarefa(t);
        }
    }

    @Test
    public void selectTabelaTest() {
        int n = 10;
        var exemplosLocais = new ArrayList<Tarefa>();
        for (int i = 0; i < n; i++) {
            exemplosLocais.add(new Tarefa("Exemplo " + i, new Date(), "Titulo " + i));
            _dbc.setTarefa(exemplosLocais.get(i));
        }

        var dadosDaDB = _dbc.getTabela("Tarefa");
        for (var ex : exemplosLocais) {
            assertTrue(dadosDaDB.contains(ex));
        }
        exemplosUsados.addAll(exemplosLocais);
    }

    @Test
    public void setTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Set", new Date(), "Teste Set");
        _dbc.setTarefa(exemplo);
        assertNotNull(_dbc.getTarefaPorID(exemplo.getId()));
        exemplosUsados.add(exemplo);
    }

    @Test
    public void removeTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Remove", new Date(), "Teste Remove");
        _dbc.setTarefa(exemplo);
        _dbc.removeTarefa(exemplo);
        assertNull(_dbc.getTarefaPorID(exemplo.getId()));
    }

    @Test
    public void editaTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Update", new Date(), "Teste Update");
        _dbc.setTarefa(exemplo);
        String tituloOriginal = exemplo.getTitulo();
        String descOriginal = exemplo.getDescricao();

        exemplo.setTitulo("Titulo Editado");
        exemplo.setDescricao("Desc Editada");

        _dbc.updateTarefa(exemplo);
        var exemploDaDB = _dbc.getTarefaPorID(exemplo.getId());
        assertNotEquals(exemploDaDB.getTitulo(), tituloOriginal);
        assertNotEquals(exemploDaDB.getDescricao(), descOriginal);
        exemplosUsados.add(exemplo);
    }

    @Test
    public void singletonReferenciaTest() {
        assertNotNull(_dbc);
    }
}