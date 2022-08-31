package no.noroff.assignment3.controllers;

import no.noroff.assignment3.mappers.CharacterMapper;
import no.noroff.assignment3.moduls.Character;
import no.noroff.assignment3.moduls.dtos.CharacterDTO;
import no.noroff.assignment3.services.Character.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Character character = characterService.findById(id);
        CharacterDTO characterDTO= characterMapper.characterToCharacterDTO(character);
        return ResponseEntity.ok(characterDTO);
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