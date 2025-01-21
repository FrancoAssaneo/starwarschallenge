package com.starwars.app.controller;

import com.starwars.app.model.People.PeopleListResponseDTO;
import com.starwars.app.model.People.PeopleResponseDTO;
import com.starwars.app.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests related to people.
 * This controller provides endpoints to retrieve a list of people and a single person by their ID.
 * It communicates with the {@link PeopleService} to fetch the required data and returns it in
 * the form of {@link PeopleListResponseDTO} and {@link PeopleResponseDTO} respectively.
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/all")
    public ResponseEntity<PeopleListResponseDTO> getPeople(@RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(required = false) String name) {
        PeopleListResponseDTO peopleListResponseDTO =  peopleService.getPeople(page, size, name);
        return ResponseEntity.ok(peopleListResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeopleResponseDTO> getPersonById(@PathVariable int id) {
        PeopleResponseDTO response = peopleService.getPersonById(id);
        return ResponseEntity.ok(response);
    }
}