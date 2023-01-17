package com.poo2.poo2_l.controllers;

import com.poo2.poo2_l.models.Tarefa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DatabaseController {
    /*
    Classe que representa um objeto utilizado para realizar operações sobre o banco de dados
    configuração do hibernate/banco de dados é feita no construtor, configurado para utilizar uma instancia local de PostgreSQL
    é utilizado o padrão singleton para manter uma unica referencia sobre controle
    */

    private static DatabaseController _dbControl;
    private SessionFactory sessionFactory;

    private DatabaseController() {
        // configurações específicas do banco de dados e hibernate em resources/*package*/hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseController getDBControl() {
        // singleton
        if (_dbControl == null)
            _dbControl = new DatabaseController();

        return _dbControl;
    }

    public void setTarefa(Tarefa e) {
        // adiciona uma instancia da classe Tarefa ao banco de dados
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(e);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List getTabela(String nome) {
        // retorna uma busca no banco de dados por todas as entradas da tabela com um nome especifico
        List result = null;
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            result = session.createQuery("from " + nome).list();
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

    public void updateTarefa(Tarefa t) {
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void removeTarefa(Tarefa t)
    {
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(t);
            session.getTransaction().commit();
            session.close();
        }
    }
}
