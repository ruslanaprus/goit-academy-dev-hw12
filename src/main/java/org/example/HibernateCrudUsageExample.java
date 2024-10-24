package org.example;

import org.example.config.HibernateConfig;
import org.example.model.Client;
import org.example.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateCrudUsageExample {
    private static final Logger logger = LoggerFactory.getLogger(HibernateCrudUsageExample.class);

    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        // create a new Client
        Client newClient = new Client();
        newClient.setName("Kitten Paw");
        clientService.saveClient(newClient);

        // update the client
        long clientIdToUpdate = 16L;
        newClient.setEmail("kitten@example.com");
        clientService.updateClient(clientIdToUpdate, newClient);
        logger.info("Client updated: {}", newClient);


        long clientIdToDelete = 16L;
        clientService.deleteClient(clientIdToDelete);

        // read and display Client by id
        long clientId = 5L;
        Client client = clientService.findClientById(clientId);
        if (client != null) {
            logger.info("Client with ID {} found: {}", clientId, client);
        } else {
            logger.warn("No client found with ID {}", clientId);
        }

        // find and display all clients
        List<Client> clients = clientService.findAll();
        if (!clients.isEmpty()) {
            logger.info("Found {} clients.", clients.size());
            for (Client c : clients) {
                logger.info("Client: {}", c);
            }
        } else {
            logger.warn("No clients found.");
        }

        HibernateConfig.getInstance().close();
        logger.info("Hibernate session factory closed.");
    }
}