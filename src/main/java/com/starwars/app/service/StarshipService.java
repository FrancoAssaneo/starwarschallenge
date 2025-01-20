package com.starwars.app.service;

import com.starwars.app.config.SwApiConfig;
import com.starwars.app.model.Starship.StarshipListResponseDTO;
import com.starwars.app.model.Starship.StarshipResponseDTO;
import com.starwars.app.util.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service class for retrieving starship data from the Star Wars API.
 * This service provides methods to fetch a paginated list of starships
 * or retrieve details of a specific starship by ID.
 */
@Service
public class StarshipService {

    private final RestTemplate restTemplate;
    private final SwApiConfig swApiConfig;

    /**
     * Constructs a {@code StarshipService} with the necessary dependencies.
     *
     * @param restTemplate the {@link RestTemplate} used for making API requests
     * @param swApiConfig  the {@link SwApiConfig} containing API endpoint configurations
     */
    @Autowired
    public StarshipService(RestTemplate restTemplate, SwApiConfig swApiConfig) {
        this.restTemplate = restTemplate;
        this.swApiConfig = swApiConfig;
    }

    /**
     * Retrieves a paginated list of starships from the Star Wars API.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a {@link StarshipListResponseDTO} containing the list of starships
     */
    public StarshipListResponseDTO getStarships(int page, int size) {
        String url = UriComponentsBuilder.fromHttpUrl(swApiConfig.getStarshipUrl())
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        ResponseEntity<StarshipListResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), StarshipListResponseDTO.class);

        return response.getBody();
    }

    /**
     * Retrieves details of a specific starship by ID from the Star Wars API.
     *
     * @param id the ID of the starship to retrieve
     * @return a {@link StarshipResponseDTO} containing details of the requested starship
     */
    public StarshipResponseDTO getStarshipById(int id) {
        String url = swApiConfig.getStarshipUrl() + id;
        ResponseEntity<StarshipResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), StarshipResponseDTO.class);

        return response.getBody();
    }
}