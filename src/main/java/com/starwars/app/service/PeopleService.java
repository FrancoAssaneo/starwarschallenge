package com.starwars.app.service;

import com.starwars.app.config.SwApiConfig;
import com.starwars.app.model.People.PeopleListDataDTO;
import com.starwars.app.model.People.PeopleListResponseDTO;
import com.starwars.app.model.People.PeopleResponseDTO;
import com.starwars.app.util.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * Retrieves a paginated list of people from the Star Wars API (SWAPI).
     * Optionally filters the results by name if provided.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @param name (optional) the name to filter the results by
     * @return a {@link PeopleListResponseDTO} containing the filtered results
     */
    public PeopleListResponseDTO getPeople(int page, int size, String name) {
        PeopleListResponseDTO responseBody = fetchPeopleFromAPI(page, size);
        if (responseBody == null || responseBody.getResults() == null) {
            return new PeopleListResponseDTO("ok", 0, 0, Collections.emptyList());
        }
        List<PeopleListDataDTO> filteredResults = responseBody.getResults();

        if (name != null && !name.isEmpty()) {
            filteredResults = filterPeopleByName(filteredResults, name);
        }
        int totalRecords = filteredResults.size();
        int totalPages = (int) Math.ceil((double) totalRecords / size);

        return new PeopleListResponseDTO("ok", totalRecords, totalPages, filteredResults);
    }

    /**
     * Fetches a paginated list of people from the Star Wars API (SWAPI).
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a {@link PeopleListResponseDTO} containing the fetched data
     */
    private PeopleListResponseDTO fetchPeopleFromAPI(int page, int size) {
        String url = UriComponentsBuilder.fromHttpUrl(swApiConfig.getPeopleUrl())
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        ResponseEntity<PeopleListResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), PeopleListResponseDTO.class);

        return response.getBody();
    }

    /**
     * Filters a list of people based on the provided name.
     * The filter is case-insensitive and performs a partial match.
     *
     * @param people the list of people to filter
     * @param name the name to filter by
     * @return a filtered list of {@link PeopleListDataDTO} containing only matching results
     */
    private List<PeopleListDataDTO> filterPeopleByName(List<PeopleListDataDTO> people, String name) {
        return people.stream()
                .filter(person -> person.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
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
