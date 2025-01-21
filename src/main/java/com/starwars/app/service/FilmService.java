package com.starwars.app.service;

import com.starwars.app.config.SwApiConfig;
import com.starwars.app.model.Film.FilmByIdResponseDTO;
import com.starwars.app.model.Film.FilmListDataDTO;
import com.starwars.app.model.Film.FilmResponseDTO;
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
 * Service class for retrieving film data from the Star Wars API.
 * This service communicates with an external API to fetch film details,
 * providing methods to retrieve a paginated list of films or a specific film by ID.
 */
@Service
public class FilmService {

    private final RestTemplate restTemplate;
    private final SwApiConfig swApiConfig;

    /**
     * Constructs a {@code FilmService} with the required dependencies.
     *
     * @param restTemplate the {@link RestTemplate} used for making API requests
     * @param swApiConfig  the {@link SwApiConfig} containing API endpoint configurations
     */
    @Autowired
    public FilmService(RestTemplate restTemplate, SwApiConfig swApiConfig) {
        this.restTemplate = restTemplate;
        this.swApiConfig = swApiConfig;
    }

    /**
     * Retrieves a paginated list of films from the Star Wars API (SWAPI).
     * Optionally filters the results by title if provided.
     *
     * @param page  the page number to retrieve
     * @param size  the number of records per page
     * @param title (optional) the title to filter the results by
     * @return a {@link FilmResponseDTO} containing the filtered results
     */
    public FilmResponseDTO getFilms(int page, int size, String title) {
        FilmResponseDTO responseBody = fetchFilmsFromAPI(page, size);
        if (responseBody == null || responseBody.getResult() == null) {
            return new FilmResponseDTO("ok", Collections.emptyList());
        }
        List<FilmListDataDTO> filteredResults = responseBody.getResult();

        if (title != null && !title.isEmpty()) {
            filteredResults = filterFilmsByTitle(filteredResults, title);
        }

        int totalRecords = filteredResults.size();
        int totalPages = (int) Math.ceil((double) totalRecords / size);

        return new FilmResponseDTO("ok", filteredResults);
    }

    /**
     * Fetches a paginated list of films from the Star Wars API (SWAPI).
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a {@link FilmResponseDTO} containing the fetched data
     */
    private FilmResponseDTO fetchFilmsFromAPI(int page, int size) {
        String url = UriComponentsBuilder.fromHttpUrl(swApiConfig.getFilmUrl())
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        ResponseEntity<FilmResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), FilmResponseDTO.class);

        return response.getBody();
    }

    /**
     * Filters a list of films based on the provided title.
     * The filter is case-insensitive and performs a partial match.
     *
     * @param films the list of films to filter
     * @param title the title to filter by
     * @return a filtered list of {@link FilmListDataDTO} containing only matching results
     */
    private List<FilmListDataDTO> filterFilmsByTitle(List<FilmListDataDTO> films, String title) {
        return films.stream()
                .filter(film -> film.getFilm().getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific film by its ID from the Star Wars API.
     *
     * @param id the ID of the film to retrieve
     * @return a {@link FilmByIdResponseDTO} containing details of the requested film
     */
    public FilmByIdResponseDTO getFilmById(int id) {
        String url = swApiConfig.getFilmUrl() + id;
        ResponseEntity<FilmByIdResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), FilmByIdResponseDTO.class);

        return response.getBody();
    }
}
