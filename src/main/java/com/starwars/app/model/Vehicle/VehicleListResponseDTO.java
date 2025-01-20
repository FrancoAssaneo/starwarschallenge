package com.starwars.app.model.Vehicle;

import java.util.List;

/**
 * Data Transfer Object (DTO) for the response containing a list of vehicles.
 * This class represents the response structure when retrieving a list of vehicles. It includes a message
 * indicating the response status or error, the total number of records, the total number of pages,
 * pagination links for previous and next pages, and a list of {@link VehicleListDataDTO} objects representing individual vehicles.
 */
public class VehicleListResponseDTO {

    private String message;
    private int totalRecord;
    private int totalPages;
    private String previous;
    private String next;
    private List<VehicleListDataDTO> results;

    public VehicleListResponseDTO() {
        //No-Args Constructor
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
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

    public List<VehicleListDataDTO> getResults() {
        return results;
    }

    public void setResults(List<VehicleListDataDTO> results) {
        this.results = results;
    }
}
