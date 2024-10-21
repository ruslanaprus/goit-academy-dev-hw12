package org.example;

import org.example.config.HibernateUtil;
import org.example.entity.Client;
import org.example.service.ClientService;

public class HibernateCrudUsageExample {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        // create new Client
        Client newClient = new Client();
        newClient.setName("Kitten Paw");
        clientService.saveClient(newClient);

        // read and display Client by id
        Client client = clientService.findClientById(4L);
        System.out.println("Found client: " + client.getName());

        // update client
        client.setName("Meow Meow");
        client.setEmail("cute@example.com");
        clientService.updateClient(client);

        // delete Client
        Client clientBob = clientService.findClientById(2L);
        clientService.deleteClient(clientBob);

        HibernateUtil.getInstance().close();
    }
}