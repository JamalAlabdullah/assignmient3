package no.noroff.assignment3.controllers;

import no.noroff.assignment3.moduls.Character;
import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.services.Character.CharacterService;
import no.noroff.assignment3.services.Movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final MovieService movieService;

    public CharacterController(CharacterService characterService, MovieService movieService) {
        this.characterService = characterService;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Character character) {
        characterService.add(character);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Character character, @PathVariable int id) {
        // Validates if body is correct
        if(id != character.getCharacterId())
            return ResponseEntity.badRequest().build();
        characterService.update(character);
        return ResponseEntity.noContent().build();
    }


}