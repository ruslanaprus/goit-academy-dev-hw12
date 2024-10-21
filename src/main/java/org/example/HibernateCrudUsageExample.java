package org.example;

import org.example.config.HibernateUtil;
import org.example.entity.Client;
import org.example.service.ClientService;

public class HibernateCrudUsageExample {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        // Створення нової Client
        Client newClient = new Client();
        newClient.setName("John Doe");
        clientService.saveClient(newClient);

        // Читаємо та виводимо Client по id
        Client client = clientService.findClientById(1L);
        System.out.println("Found client: " + client.getName());

        // Оновлюємо client
        client.setName("Jane Doe");
        clientService.updateClient(client);

        // Видаляємо Client
        clientService.deleteClient(client);

        HibernateUtil.getInstance().close();
    }
}