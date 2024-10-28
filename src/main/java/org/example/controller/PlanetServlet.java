package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Planet;
import org.example.service.PlanetService;
import org.example.config.TemplateConfig;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/planetManager", "/createPlanetForm", "/findPlanetByIdForm", "/findPlanetById",
        "/findAllPlanets", "/createPlanet", "/updatePlanet", "/updatePlanetForm",
        "/deletePlanetByIdForm", "/deletePlanetById"})
public class PlanetServlet extends HttpServlet {
    private final TemplateConfig templateConfig = new TemplateConfig();
    private final PlanetService planetService = new PlanetService();

    public PlanetServlet() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        Context context = new Context();

        switch (servletPath) {
            case "/planetManager" -> templateConfig.process("planet", context, res);
            case "/createPlanetForm" -> {
                context.setVariable("action", "createPlanetForm");
                templateConfig.process("planet", context, res);
            }
            case "/findPlanetByIdForm" -> {
                context.setVariable("action", "findPlanetByIdForm");
                templateConfig.process("planet", context, res);
            }
            case "/findPlanetById" -> {
                String planetId = req.getParameter("planetId");
                Planet planet = planetService.findPlanetById(planetId);
                context.setVariable("planet", planet);
                context.setVariable("action", "planetDetails");
                templateConfig.process("planet", context, res);
            }
            case "/findAllPlanets" -> {
                List<Planet> planets = planetService.findAll();
                context.setVariable("planets", planets);
                context.setVariable("action", "allPlanetDetails");
                templateConfig.process("planet", context, res);
            }
            case "/updatePlanetForm" -> {
                context.setVariable("action", "updatePlanetForm");
                templateConfig.process("planet", context, res);
            }
            case "/deletePlanetByIdForm" -> {
                context.setVariable("action", "deletePlanetByIdForm");
                templateConfig.process("planet", context, res);
            }
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        String planetId = req.getParameter("planetId");
//        String name = req.getParameter("name");
//        String galaxy = req.getParameter("galaxy");
//
//        Planet planet = new Planet(planetId, name, galaxy);
//
//        planetService.savePlanet(planet);
//
//        res.sendRedirect("/findAllPlanets?message=Planet created successfully.");
//    }
}