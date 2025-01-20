package com.starwars.app.model.People;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for the response containing a single person.
 * This class represents the response structure for a person, including a message indicating the
 * response status or error and the result, which contains detailed information about the person.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleResponseDTO {

    private String message;
    private Result result;

    /**
     * Represents the detailed data of a person in the response.
     * This nested class contains the properties, description, unique identifiers, and versioning
     * information related to a person.
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
     * Represents the properties of a person, including personal attributes like name, height, mass,
     * and other characteristics.
     * This nested class contains details like the person's name, appearance, birth year, gender,
     * and links to their homeworld and other related resources.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        private String name;
        private String height;
        private String mass;

        @JsonProperty("hair_color")
        private String hairColor;

        @JsonProperty("skin_color")
        private String skinColor;

        @JsonProperty("eye_color")
        private String eyeColor;

        @JsonProperty("birth_year")
        private String birthYear;

        private String gender;
        private String created;
        private String edited;
        private String homeworld;
        private String url;
    }
}