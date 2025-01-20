package com.starwars.app.model.Starship;

/**
 * Data Transfer Object (DTO) representing a starship in the list.
 * This class holds the basic information for a starship, including a unique identifier (`uid`),
 * the name of the starship, and a URL pointing to the detailed resource of the starship.
 */
public class StarshipListDTO {

    private String uid;
    private String name;
    private String url;

    public StarshipListDTO() {
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
