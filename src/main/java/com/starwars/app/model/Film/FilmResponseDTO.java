package com.starwars.app.model.Film;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the response containing a list of films.
 * This class represents the response structure when retrieving a list of films. It includes a message
 * indicating the response status or error, and a list of {@link FilmListDataDTO} objects representing
 * the individual films.
 */
public class FilmResponseDTO {
    private String message;
    private List<FilmListDataDTO> result;

    public FilmResponseDTO() {
        //No-Args Constructor
    }

    public FilmResponseDTO(String message, List<FilmListDataDTO> result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FilmListDataDTO> getResult() {
        return result;
    }

    public void setResult(List<FilmListDataDTO> result) {
        this.result = result;
    }
}
