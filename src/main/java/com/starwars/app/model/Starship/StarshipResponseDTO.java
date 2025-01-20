package com.starwars.app.model.Starship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the response containing a single starship.
 * This class represents the response structure for a starship, including a message indicating the
 * response status or error and the result, which contains detailed information about the starship.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarshipResponseDTO {

    private String message;
    private Result result;

    /**
     * Represents the detailed data of a starship in the response.
     * This nested class contains the properties, description, unique identifiers, and versioning
     * information related to the starship.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private Properties properties;
        private String description;
        private String _id;
        private String uid;
        private int __v;
    }

    /**
     * Represents the properties of a starship, including technical and physical attributes.
     * This nested class contains details like the starship's class, cost, speed, cargo capacity,
     * and other specifications that describe the starship's characteristics.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        @JsonProperty("starship_class")
        private String starshipClass;

        @JsonProperty("cost_in_credits")
        private String costInCredits;

        @JsonProperty("max_atmosphering_speed")
        private String maxAtmospheringSpeed;

        @JsonProperty("cargo_capacity")
        private String cargoCapacity;

        @JsonProperty("hyperdrive_rating")
        private String hyperdriveRating;

        private String manufacturer;
        private String model;
        private String lenght;
        private String crew;
        private String passengers;
        private String mglt;
        private String consumables;
        private List<String> pilots;
    }
}