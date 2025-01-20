package com.starwars.app.service;

import com.starwars.app.config.SwApiConfig;
import com.starwars.app.model.Vehicle.VehicleListResponseDTO;
import com.starwars.app.model.Vehicle.VehicleResponseDTO;
import com.starwars.app.util.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service class for retrieving vehicle data from the Star Wars API.
 * This service provides methods to fetch a paginated list of vehicles
 * or retrieve details of a specific vehicle by ID.
 */
@Service
public class VehicleService {

    private final RestTemplate restTemplate;
    private final SwApiConfig swApiConfig;

    /**
     * Constructs a {@code VehicleService} with the necessary dependencies.
     *
     * @param restTemplate the {@link RestTemplate} used for making API requests
     * @param swApiConfig  the {@link SwApiConfig} containing API endpoint configurations
     */
    @Autowired
    public VehicleService(RestTemplate restTemplate, SwApiConfig swApiConfig) {
        this.restTemplate = restTemplate;
        this.swApiConfig = swApiConfig;
    }

    /**
     * Retrieves a paginated list of vehicles from the Star Wars API.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @return a {@link VehicleListResponseDTO} containing the list of vehicles
     */
    public VehicleListResponseDTO getVehicles(int page, int size) {
        String url = UriComponentsBuilder.fromHttpUrl(swApiConfig.getVehicleUrl())
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        ResponseEntity<VehicleListResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), VehicleListResponseDTO.class);

        return response.getBody();
    }

    /**
     * Retrieves details of a specific vehicle by ID from the Star Wars API.
     *
     * @param id the ID of the vehicle to retrieve
     * @return a {@link VehicleResponseDTO} containing details of the requested vehicle
     */
    public VehicleResponseDTO getVehicleById(int id) {
        String url = swApiConfig.getVehicleUrl() + id;
        ResponseEntity<VehicleResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.GET, RestTemplateUtils.createHttpEntity(), VehicleResponseDTO.class);

        return response.getBody();
    }
}
