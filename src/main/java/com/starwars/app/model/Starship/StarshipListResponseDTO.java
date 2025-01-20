package com.starwars.app.model.Starship;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the response containing a list of starships.
 * This class represents the response structure when retrieving a list of starships. It includes a message
 * indicating the response status or error, the total number of records, the total number of pages,
 * pagination links for previous and next pages, and a list of {@link StarshipListDTO} objects representing individual starships.
 */
public class StarshipListResponseDTO {

    private String message;
    private int totalRecords;
    private int totalPages;
    private String previous;
    private String next;
    private List<StarshipListDTO> results;

    public StarshipListResponseDTO() {
        //No-Args Constructor
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<StarshipListDTO> getResults() {
        return results;
    }

    public void setResults(List<StarshipListDTO> results) {
        this.results = results;
    }
}
