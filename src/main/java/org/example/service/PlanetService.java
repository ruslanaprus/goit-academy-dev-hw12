package org.example.service;

import org.example.dao.GenericDao;
import org.example.model.Planet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlanetService {
    private static final Logger logger = LoggerFactory.getLogger(PlanetService.class);
    private final GenericDao<Planet, String> planetDao = new GenericDao<>(Planet.class);

    public void savePlanet(Planet planet) {
        logger.info("Saving planet: {}", planet);
        planetDao.save(planet);
    }

    public Planet findPlanetById(String id) {
        logger.info("Finding planet with ID: {}", id);
        return planetDao.findById(id);
    }

    public List<Planet> findAll() {
        return planetDao.findAll();
    }

    public void updatePlanet(String id, Planet planet) {
        planetDao.update(id, planet);
    }

    public void deletePlanet(String id) {
        logger.info("Deleting planet with id: {}", id);
        planetDao.delete(id);
        logger.info("Planet deleted successfully");
    }
}