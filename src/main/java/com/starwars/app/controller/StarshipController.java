package com.starwars.app.controller;

import com.starwars.app.model.Starship.StarshipListResponseDTO;
import com.starwars.app.model.Starship.StarshipResponseDTO;
import com.starwars.app.service.StarshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests related to starships.
 * This controller provides endpoints to retrieve a list of starships and a single starship by its ID.
 * It communicates with the {@link StarshipService} to fetch the required data and returns it in
 * the form of {@link StarshipListResponseDTO} and {@link StarshipResponseDTO} respectively.
 */
@RestController
@RequestMapping("/starships")
public class StarshipController {

    private final StarshipService starshipService;

    private StarshipController(StarshipService starshipService) {
        this.starshipService = starshipService;
    }

    @GetMapping("/all")
    public ResponseEntity<StarshipListResponseDTO> getPeople(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        StarshipListResponseDTO starshipListResponseDTO =  starshipService.getStarships(page, size);
        return ResponseEntity.ok(starshipListResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarshipResponseDTO> getPersonById(@PathVariable int id) {
        StarshipResponseDTO response = starshipService.getStarshipById(id);
        return ResponseEntity.ok(response);
    }
}
