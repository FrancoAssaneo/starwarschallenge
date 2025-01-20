package com.starwars.app.model.Vehicle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the response containing a single vehicle.
 * This class represents the response structure for a vehicle, including a message indicating the
 * response status or error and the result, which contains detailed information about the vehicle.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleResponseDTO {

    private String message;
    private Result result;

    /**
     * Represents the detailed data of a vehicle in the response.
     * This nested class contains the properties, description, unique identifiers, and versioning
     * information related to the vehicle.
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
     * Represents the properties of a vehicle, including technical and physical attributes.
     * This nested class contains details like the vehicle's cost, speed, cargo capacity,
     * class, and other specifications that describe the vehicle's characteristics.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {

        @JsonProperty("cost_in_credits")
        private String costInCredits;

        @JsonProperty("max_atmosphering_speed")
        private String maxAtmospheringSpeed;

        @JsonProperty("cargo_capacity")
        private String cargoCapacity;

        @JsonProperty("vehicle_class")
        private String vehicleClass;

        private String manufacturer;
        private String model;
        private String lenght;
        private String crew;
        private String passengers;
        private String consumables;
        private List<String> pilots;
        private List<String> films;
        private String created;
        private String edited;
        private String name;
        private String url;
    }
}