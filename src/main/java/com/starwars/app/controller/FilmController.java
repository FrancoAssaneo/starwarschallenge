package com.starwars.app.controller;

import com.starwars.app.model.Film.FilmByIdResponseDTO;
import com.starwars.app.model.Film.FilmResponseDTO;
import com.starwars.app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests related to films.
 * This controller provides endpoints to retrieve a list of films and a single film by its ID.
 * It communicates with the {@link FilmService} to fetch the required data and returns it in
 * the form of {@link FilmResponseDTO} and {@link FilmByIdResponseDTO} respectively.
 */
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/all")
    public ResponseEntity<FilmResponseDTO> getFilms(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(required = false) String title) {
        FilmResponseDTO filmResponseDTO =  filmService.getFilms(page, size, title);
        return ResponseEntity.ok(filmResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmByIdResponseDTO> getFilmById(@PathVariable int id) {
        FilmByIdResponseDTO response = filmService.getFilmById(id);
        return ResponseEntity.ok(response);
    }
}
