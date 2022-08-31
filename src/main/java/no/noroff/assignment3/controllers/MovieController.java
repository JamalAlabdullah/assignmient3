package no.noroff.assignment3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.assignment3.mappers.MovieMapper;
import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.moduls.dtos.MovieDTO;
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

    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, CharacterService characterService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.characterService = characterService;
        this.movieMapper = movieMapper;
    }

    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Could not find movies",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Collection<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @Operation(summary = "Get a Movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        MovieDTO movieDTO= movieMapper.movieToMovieDTO(movie);
        return ResponseEntity.ok(movieDTO);
    }


    @Operation(summary = "Add new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Could not find movie to add",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity add(@RequestBody Movie movie) {
        movieService.add(movie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "delete a Movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie does not exist with supplied ID",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update a Movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie does not exist with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id) {
        // Validates if body is correct
        if(id != movie.getId())
            return ResponseEntity.badRequest().build();
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update characters in a Movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie does not exist with supplied ID",
                    content = @Content)
    })
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

    @Operation(summary = "Get characters in a Movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Movie does not exist with supplied ID",
                    content = @Content)
    })
    @GetMapping("{id}/characters")
    public ResponseEntity<Collection<Character>> getAllCharacters(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id).getCharacters());
    }

}

