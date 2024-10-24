package org.example.dao;

import org.example.config.HibernateConfig;
import org.example.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientDao {
    private static final Logger logger = LoggerFactory.getLogger(ClientDao.class);
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    public void save(Client client) {
        logger.debug("Opening session for saving client");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            logger.debug("Client saved successfully: {}", client);
        }
    }

    public Client findById(Long id) {
        logger.debug("Opening session for finding client with ID: {}", id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var client = session.get(Client.class, id);
            transaction.commit();
            return client;
        }
    }

    public List<Client> findAll() {
        logger.debug("Opening session for finding all clients");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var clientsQuery = session.createQuery("from Client", Client.class);
            clientsQuery.setFirstResult(5);
            clientsQuery.setMaxResults(10);
            var clients = clientsQuery.getResultList();
            transaction.commit();
            return clients;
        }
    }

    public void update(Long id, Client updatedClient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var client = session.get(Client.class, id);
            if (client == null) {
                throw new IllegalArgumentException("Client with id " + id + " not found");
            }

            if (updatedClient.getName() != null) {
                client.setName(updatedClient.getName());
            }

            if (updatedClient.getEmail() != null) {
                client.setEmail(updatedClient.getEmail());
            }

            session.merge(client);
            transaction.commit();
        }
    }

    public void delete(Long id) {
        logger.debug("Opening session for deleting client with id: {}", id);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            var client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
                logger.debug("Client deleted successfully: {}", client);
            } else {
                logger.warn("Client with id {} not found. Did not delete", id);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error("Error while deleting client with id: {}, {}", id, e.getMessage());
        }
    }
}
