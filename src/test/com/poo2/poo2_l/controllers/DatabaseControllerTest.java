package com.poo2.poo2_l.controllers;

import java.util.*;

import com.poo2.poo2_l.models.IEntidade;
import com.poo2.poo2_l.models.Tarefa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseControllerTest {
    private static DatabaseController _dbc;
    private static List<IEntidade> exemplosUsados;

    @BeforeAll
    public static void setup() {
        _dbc = DatabaseController.getDBControl();
        exemplosUsados = new ArrayList<IEntidade>();
    }

    @AfterAll
    public static void limparExemplos() {
        for (var t : exemplosUsados) {
            _dbc.removeEntidade(t);
        }
    }

    @Test
    public void selectTabelaTest() {
        int n = 10;
        var exemplosLocais = new ArrayList<Tarefa>();
        for (int i = 0; i < n; i++) {
            exemplosLocais.add(new Tarefa("Exemplo " + i, new Date(), "Titulo " + i));
            _dbc.setEntidade(exemplosLocais.get(i));
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
        _dbc.setEntidade(exemplo);
        assertNotNull(_dbc.getEntidadePorID(exemplo.getId(), exemplo.getClass()));
        exemplosUsados.add(exemplo);
    }

    @Test
    public void removeTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Remove", new Date(), "Teste Remove");
        _dbc.setEntidade(exemplo);
        _dbc.removeEntidade(exemplo);
        assertNull(_dbc.getEntidadePorID(exemplo.getId(), exemplo.getClass()));
    }

    @Test
    public void editaTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Update", new Date(), "Teste Update");
        _dbc.setEntidade(exemplo);
        String tituloOriginal = exemplo.getTitulo();
        String descOriginal = exemplo.getDescricao();

        exemplo.setTitulo("Titulo Editado");
        exemplo.setDescricao("Desc Editada");

        _dbc.updateEntidade(exemplo);
        var exemploDaDB = _dbc.getEntidadePorID(exemplo.getId(), exemplo.getClass());
        assertNotEquals(exemploDaDB.getTitulo(), tituloOriginal);
        assertNotEquals(exemploDaDB.getDescricao(), descOriginal);
        exemplosUsados.add(exemplo);
    }

    @Test
    public void singletonReferenciaTest() {
        assertNotNull(_dbc);
    }
}