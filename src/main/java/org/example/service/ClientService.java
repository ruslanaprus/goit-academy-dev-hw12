package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.ClientDao;
import org.example.model.Client;
import org.example.util.RequestToEntityMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ClientService {
    private ClientDao clientDao = new ClientDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    public void saveClient(Client client) {
        clientDao.save(client);
    }

    public void saveClient(BufferedReader payload) throws IOException {
        Client client = objectMapper.readValue(payload, Client.class);
        clientDao.save(client);
    }

    public void saveClient(Map<String, String[]> parameterMap) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Client client = RequestToEntityMapper.mapToEntity(parameterMap, Client.class);
        System.out.println("customer = " + client);
        clientDao.save(client);
        System.out.println("customer = " + client);
    }

    public Client findClientById(Long id) {
        return clientDao.findById(id);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public void updateClient(Client client) {
        clientDao.update(client);
    }

    public void deleteClient(Client client) {
        clientDao.delete(client);
    }
}