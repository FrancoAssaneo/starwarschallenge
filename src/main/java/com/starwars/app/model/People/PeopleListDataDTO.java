package com.starwars.app.model.People;

/**
 * Data Transfer Object (DTO) representing a person in the list.
 * This class holds the basic information for a person, including a unique identifier (`uid`),
 * the name of the person, and a URL pointing to the detailed resource of the person.
 */
public class PeopleListDataDTO {

    private String uid;
    private String name;
    private String url;

    public PeopleListDataDTO() {
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
