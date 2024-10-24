package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.ClientDao;
import org.example.model.Client;
import org.example.util.RequestToEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientDao clientDao = new ClientDao();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveClient(Client client) {
        logger.info("Saving client: {}", client);
        clientDao.save(client);
    }

    public void saveClient(BufferedReader payload) throws IOException {
        Client client = objectMapper.readValue(payload, Client.class);
        logger.info("Saving client from payload: {}", client);
        clientDao.save(client);
        logger.info("Client saved successfully from payload");
    }

    public void saveClient(Map<String, String[]> parameterMap) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Client client = RequestToEntityMapper.mapToEntity(parameterMap, Client.class);
        logger.info("Saving client from parameters: {}", client);
        clientDao.save(client);
        logger.info("Client saved successfully from parameters");
    }

    public Client findClientById(Long id) {
        logger.info("Finding client with ID: {}", id);
        return clientDao.findById(id);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public void updateClient(Client client) {
        clientDao.update(client);
    }

    public void deleteClient(Long id) {
        logger.info("Deleting client with id: {}", id);
        clientDao.delete(id);
        logger.info("Client deleted successfully");
    }
}