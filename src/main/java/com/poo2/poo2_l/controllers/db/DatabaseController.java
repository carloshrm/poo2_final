package com.poo2.poo2_l.controllers.db;

import com.poo2.poo2_l.models.IEntidade;
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
            System.out.println(e.getCause());
        }
    }

    public static DatabaseController getDBControl() {
        // singleton
        if (_dbControl == null)
            _dbControl = new DatabaseController();

        return _dbControl;
    }

    public void setEntidade(IEntidade e) {
        // adiciona uma instancia de uma das entidades no banco de dados
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(e);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List getTabela(String nome) {
        // retorna uma busca no banco de dados por todas as entradas na tabela com o nome passado por parametro
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

    public <T extends IEntidade> T getEntidadePorID(Long id, Class<T> classe) {
        T result = null;
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            result = classe.cast(session.get(classe, id));
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

    public void updateEntidade(IEntidade e) {
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(e);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void removeEntidade(IEntidade e) {
        synchronized (this) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(e);
            session.getTransaction().commit();
            session.close();
        }
    }
}
