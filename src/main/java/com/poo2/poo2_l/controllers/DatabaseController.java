package com.poo2.poo2_l.controllers;

import com.poo2.poo2_l.models.Tarefa;
import javafx.scene.chart.PieChart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.Database;

import java.util.Date;
import java.util.List;

public class DatabaseController {

    private SessionFactory sessionFactory;
    private static DatabaseController _dbControl;

    private DatabaseController() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseController getDBControl()
    {
        if (_dbControl == null)
            _dbControl = new DatabaseController();

        return _dbControl;
    }

    public void setEntidade(Object e)
    {
        synchronized (this)
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List getTabela(String nome)
    {
        List result = null;
        synchronized (this)
        {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            result = session.createQuery("from " + nome).list();
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }
}
