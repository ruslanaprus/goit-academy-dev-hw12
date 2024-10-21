package org.example.service;

import org.example.dao.ClientDao;
import org.example.entity.Client;

public class ClientService {
    private ClientDao clientDao = new ClientDao();

    public void saveClient(Client person) {
        clientDao.save(person);
    }

    public Client findClientById(Long id) {
        return clientDao.findById(id);
    }

    public void updateClient(Client client) {
        clientDao.update(client);
    }

    public void deleteClient(Client client) {
        clientDao.delete(client);
    }
}