package com.starwars.app.model.People;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the response containing a list of people.
 * This class represents the response structure when retrieving a list of people. It includes a message
 * indicating the response status or error, the total number of records, the total number of pages,
 * and a list of {@link PeopleListDataDTO} objects representing individual people.
 */
public class PeopleListResponseDTO {

    private String message;
    private int totalRecords;
    private int totalPages;
    private List<PeopleListDataDTO> results;

    public PeopleListResponseDTO() {
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

    public List<PeopleListDataDTO> getResults() {
        return results;
    }

    public void setResults(List<PeopleListDataDTO> results) {
        this.results = results;
    }
}

