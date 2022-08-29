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

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Character character) {
        characterService.add(character);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}