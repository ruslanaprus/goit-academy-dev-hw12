package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Client;
import org.thymeleaf.context.Context;

import org.example.config.TemplateConfig;
import org.example.service.ClientService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/createClientForm", "/findClientByIdForm", "/findById",
        "/findAllClients", "/createClient", "/updateClient", "/updateClientForm"})
public class TravelServlet extends HttpServlet {
    private final TemplateConfig templateConfig = new TemplateConfig();
    private final ClientService clientService = new ClientService();

    public TravelServlet() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String servletPath = req.getServletPath();
        Context context = new Context();
        switch (servletPath) {
            case "/createClientForm" -> {
                context.setVariable("action", "createClientForm");
                templateConfig.process("index", context, res);
            }
            case "/findClientByIdForm" -> {
                context.setVariable("action", "findByIdForm");
                templateConfig.process("index", context, res);
            }
            case "/findById" -> {
                String clientId = req.getParameter("clientId");
                Client clientById = clientService.findById(Long.parseLong(clientId));
                context.setVariable("action", "clientDetails");
                context.setVariable("client", clientById);
                templateConfig.process("index", context, res);
            }
            case "/findAllClients" -> {
                List<Client> clients = clientService.findAll();
                context.setVariable("action", "allClientDetails");
                context.setVariable("clients", clients);
                templateConfig.process("index", context, res);
            }
            case "/updateClientForm" -> {
                context.setVariable("action", "updateClientForm");
                templateConfig.process("index", context, res);
            }
            case "/updateClient" -> {
                String clientId = req.getParameter("clientId");
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                Client updatedClient = new Client();
                System.out.println("name is null: " + (name == null));
                if (name != null && !name.isEmpty()) {
                    updatedClient.setName(name);
                }
                if (email != null && !email.isEmpty()) {
                    updatedClient.setEmail(email);
                }
                clientService.updateClient(Long.parseLong(clientId), updatedClient);
                context.setVariable("action", "updateClient");
                templateConfig.process("index", context, res);
            }
            default -> templateConfig.process("index", context, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            clientService.saveClient(req.getReader());
            res.setStatus(200);
        } catch (Exception e) {
            res.setStatus(500);
        }
    }
}