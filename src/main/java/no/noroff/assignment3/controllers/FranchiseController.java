package no.noroff.assignment3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.assignment3.mappers.FranchiseMapper;
import no.noroff.assignment3.moduls.Character;
import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.moduls.dtos.FranchiseDTO;
import no.noroff.assignment3.services.Franchise.FranchiseService;
import no.noroff.assignment3.services.Movie.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final MovieService movieService;
    private  final FranchiseMapper franchiseMapper;

    public FranchiseController(FranchiseService franchiseService, MovieService movieService, FranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.movieService = movieService;
        this.franchiseMapper = franchiseMapper;
    }

    @Operation(summary = "Get all franchises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchises not found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Collection<Franchise>> getAll() {
        return ResponseEntity.ok(franchiseService.findAll());
    }

    @Operation(summary = "Get franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise could not be found with supplied id",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Franchise franchise = franchiseService.findById(id);
        FranchiseDTO franchiseDTO= franchiseMapper.franchiseToFranchiseDTO(franchise);
        return ResponseEntity.ok(franchiseDTO);
    }

    @Operation(summary = "Add franschise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity add(@RequestBody Franchise franchise) {
        franchiseService.add(franchise);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Delete franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found by supplied id",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "update franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found by supplied id",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/students/1
    public ResponseEntity update(@RequestBody Franchise franchise, @PathVariable int id) {
        // Validates if body is correct
        if(id != franchise.getId())
            return ResponseEntity.badRequest().build();
        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all movies in franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found by supplied id",
                    content = @Content)
    })
    @GetMapping("{id}/movies")
    public ResponseEntity<Collection<Movie>> getAllMovies(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.findById(id).getMovies());
    }

    @Operation(summary = "Get all characters in franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found by supplied id",
                    content = @Content)
    })
    @GetMapping("{id}/characters")
    public ResponseEntity<Collection<Character>> getAllCharacters(@PathVariable int id) {
        Set<Character> characterSet = new HashSet<>();
        for(Movie i : franchiseService.findById(id).getMovies()){
            characterSet.addAll(i.getCharacters());
        }
        return ResponseEntity.ok(characterSet);
    }

    @Operation(summary = "Update movies in franchise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Franchise.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Franchise not found by supplied id",
                    content = @Content)
    })
    @PutMapping("{id}/movies")
    public ResponseEntity updateMovies(@RequestBody int[] movieId, @PathVariable int id) {
        // Validates if body is correct
        Franchise fran = franchiseService.findById(id);
        Set<Movie> movies = new HashSet<>();
        for(int i : movieId){
            movies.add(movieService.findById(i));
        }
        fran.setMovies(movies);
        franchiseService.update(fran);
        return ResponseEntity.noContent().build();
    }


}