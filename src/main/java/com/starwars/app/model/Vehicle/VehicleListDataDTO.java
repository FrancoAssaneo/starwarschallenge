package com.starwars.app.model.Vehicle;

/**
 * Data Transfer Object (DTO) representing a vehicle in the list.
 * This class holds the basic information for a vehicle, including a unique identifier (`uid`),
 * the name of the vehicle, and a URL pointing to the detailed resource of the vehicle.
 */
public class VehicleListDataDTO {

    private String uid;
    private String name;
    private String url;

    public VehicleListDataDTO() {
        //No-Args Constructor
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
