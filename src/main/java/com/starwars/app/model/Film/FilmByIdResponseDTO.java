package com.starwars.app.model.Film;

/**
 * Data Transfer Object (DTO) for the response containing a single film's details by ID.
 * This class represents the response structure when retrieving a film by its ID. It includes
 * a message indicating the response status or error, and the actual film data in the {@link FilmListDataDTO} format.
 */
public class FilmByIdResponseDTO {

    private String message;
    private FilmListDataDTO result;

    public FilmByIdResponseDTO() {
        //No-Args Constructor
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FilmListDataDTO getResult() {
        return result;
    }

    public void setResult(FilmListDataDTO result) {
        this.result = result;
    }
}
