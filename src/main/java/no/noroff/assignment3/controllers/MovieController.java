package no.noroff.assignment3.controllers;

import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.services.Character.CharacterService;
import no.noroff.assignment3.services.Movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import no.noroff.assignment3.moduls.Character;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final CharacterService characterService;

    public MovieController(MovieService movieService, CharacterService characterService) {
        this.movieService = movieService;
        this.characterService = characterService;
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

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id) {
        // Validates if body is correct
        if(id != movie.getMovieId())
            return ResponseEntity.badRequest().build();
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/characters")
    public ResponseEntity updateCharacters(@RequestBody int[] characterId, @PathVariable int id) {
        // Validates if body is correct
        Movie movie = movieService.findById(id);
        Set<Character> characters = new HashSet<>();
        for(int i : characterId){
            characters.add(characterService.findById(i));
        }
        movie.setCharacters(characters);
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/characters")
    public ResponseEntity<Collection<Character>> getAllCharacters(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id).getCharacters());
    }

}

