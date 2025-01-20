package com.starwars.app.service;

import com.starwars.app.config.SwApiConfig;
import com.starwars.app.model.People.PeopleListResponseDTO;
import com.starwars.app.model.People.PeopleResponseDTO;
import com.starwars.app.util.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service class for retrieving people data from the Star Wars API.
 * This service provides methods to fetch a paginated list of Star Wars characters
 * or retrieve details of a specific character by ID.
 */
@Service
public class PeopleService {

    private final RestTemplate restTemplate;
    private final SwApiConfig swApiConfig;

    /**
     * Constructs a {@code PeopleService} with the necessary dependencies.
     *
     * @param restTemplate the {@link RestTemplate} used for making API requests
     * @param swApiConfig  the {@link SwApiConfig} containing API endpoint configurations
     */
    @Autowired
    public PeopleService(RestTemplate restTemplate, SwApiConfig swApiConfig) {
        this.restTemplate = restTemplate;
        this.swApiConfig = swApiConfig;
    }

    /**
     * Retrieves a paginated list of people (characters) from the Star Wars API.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a {@link PeopleListResponseDTO} containing the list of characters
     */
    public PeopleListResponseDTO getPeople(int page, int size) {
        String url = UriComponentsBuilder.fromHttpUrl(swApiConfig.getPeopleUrl())
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        ResponseEntity<PeopleListResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), PeopleListResponseDTO.class);

        return response.getBody();
    }

    /**
     * Retrieves details of a specific person (character) by ID from the Star Wars API.
     *
     * @param id the ID of the character to retrieve
     * @return a {@link PeopleResponseDTO} containing details of the requested character
     */
    public PeopleResponseDTO getPersonById(int id) {
        String url = swApiConfig.getPeopleUrl() + id;
        ResponseEntity<PeopleResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), PeopleResponseDTO.class);

        return response.getBody();
    }
}
