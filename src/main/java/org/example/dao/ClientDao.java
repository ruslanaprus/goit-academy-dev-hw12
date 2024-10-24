package org.example.dao;

import org.example.config.HibernateConfig;
import org.example.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDao {
    private SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    public void save(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }
    }

    public Client findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var client = session.get(Client.class, id);
            transaction.commit();
            return client;
        }
    }

    public List<Client> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var clientsQuery = session.createQuery("from Client", Client.class);
            clientsQuery.setFirstResult(1);
            clientsQuery.setMaxResults(10);
            var clients = clientsQuery.getResultList();
            transaction.commit();
            return clients;
        }
    }

    public void update(Client person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(person);
            transaction.commit();
        }
    }

    public void delete(Client person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(person);
            transaction.commit();
        }
    }
}
