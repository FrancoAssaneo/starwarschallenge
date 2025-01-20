package com.starwars.app.model.Film;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) representing the data of a film in the list.
 * This class holds the metadata and identifiers related to a film, including its description,
 * unique identifiers (`_id`, `uid`), version number (`__v`), and the detailed film information
 * represented as a {@link FilmDTO}.
 */
public class FilmListDataDTO {

    private String description;
    private String _id;
    private String uid;
    private int __v;

    @JsonProperty("properties")
    private FilmDTO film;

    public FilmListDataDTO() {
        //No-Args Constructor
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public FilmDTO getFilm() {
        return film;
    }

    public void setFilm(FilmDTO filmDTO) {
        this.film = filmDTO;
    }
}
