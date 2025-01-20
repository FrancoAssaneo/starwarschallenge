package com.starwars.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration class for accessing Star Wars API endpoints.
 * This class is responsible for storing the URLs of various Star Wars API resources, such as people,
 * films, starships, and vehicles. The URLs are injected from the application properties using
 * {@link Value} annotations and can be accessed via getter methods.
 */
@Component
public class SwApiConfig {

    @Value("${swapi.people-url}")
    private String peopleUrl;

    @Value("${swapi.film-url}")
    private String filmUrl;

    @Value("${swapi.starship-url}")
    private String starshipUrl;

    @Value("${swapi.vehicle-url}")
    private String vehicleUrl;

    /**
     * Gets the URL for the "People" endpoint in the Star Wars API.
     *
     * @return the URL for the "People" endpoint
     */
    public String getPeopleUrl() {
        return peopleUrl;
    }

    /**
     * Gets the URL for the "Film" endpoint in the Star Wars API.
     *
     * @return the URL for the "Film" endpoint
     */
    public String getFilmUrl() {
        return filmUrl;
    }

    /**
     * Gets the URL for the "Starship" endpoint in the Star Wars API.
     *
     * @return the URL for the "Starship" endpoint
     */
    public String getStarshipUrl() {
        return starshipUrl;
    }

    /**
     * Gets the URL for the "Vehicle" endpoint in the Star Wars API.
     *
     * @return the URL for the "Vehicle" endpoint
     */
    public String getVehicleUrl() {
        return vehicleUrl;
    }
}

