package no.noroff.assignment3.controllers;

import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.moduls.dtos.MovieDTO;
import no.noroff.assignment3.services.Movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Collection<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }


    @PostMapping
    public ResponseEntity add(@RequestBody Movie movie) {
        movieService.add(movie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

