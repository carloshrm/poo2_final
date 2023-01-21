package com.poo2.poo2_l.controllers;

import java.time.LocalDate;
import java.util.*;

import com.poo2.poo2_l.controllers.db.DatabaseController;
import com.poo2.poo2_l.models.IEntidade;
import com.poo2.poo2_l.models.Projeto;
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
        var r = new Random();
        var exemplosLocais = new ArrayList<IEntidade>();
        for (int i = 0; i < 100; i++) {
            exemplosLocais.add(r.nextInt() % 2 == 0
                    ? (new Tarefa("Exemplo " + i, LocalDate.now(), "Desc " + i))
                    : (new Projeto("Exemplo" + i, "Desc" + i, LocalDate.now()))
            );
            _dbc.setEntidade(exemplosLocais.get(i));
        }
        var tarefasDB = _dbc.getTabela(Tarefa.class);
        var projetosDB = _dbc.getTabela(Projeto.class);
        for (var ex : exemplosLocais) {
            assertTrue(tarefasDB.contains(ex) || projetosDB.contains(ex));
        }
        exemplosUsados.addAll(exemplosLocais);
    }

    @Test
    public void setTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Set", LocalDate.now(), "Teste Set");
        _dbc.setEntidade(exemplo);
        assertNotNull(_dbc.getEntidadePorID(exemplo.getId(), exemplo.getClass()));
        exemplosUsados.add(exemplo);
    }

    @Test
    public void setProjetoTest() {
        var exemplo = new Projeto("Titulo 1", "Descricao 1", LocalDate.now());
        _dbc.setEntidade(exemplo);
        var respostaDB = _dbc.getEntidadePorID(exemplo.getId(), exemplo.getClass());
        assertNotNull(respostaDB);
        assertTrue(respostaDB instanceof Projeto);
        exemplosUsados.add(exemplo);
    }

    @Test
    public void removeTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Remove", LocalDate.now(), "Teste Remove");
        _dbc.setEntidade(exemplo);
        _dbc.removeEntidade(exemplo);
        assertNull(_dbc.getEntidadePorID(exemplo.getId(), exemplo.getClass()));
    }

    @Test
    public void editaTarefaTest() {
        var exemplo = new Tarefa("Exemplo de Update", LocalDate.now(), "Teste Update");
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