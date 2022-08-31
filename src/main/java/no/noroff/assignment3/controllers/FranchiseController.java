package no.noroff.assignment3.controllers;

import no.noroff.assignment3.moduls.Character;
import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.moduls.Movie;
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

    public FranchiseController(FranchiseService franchiseService, MovieService movieService) {
        this.franchiseService = franchiseService;
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<Collection<Franchise>> getAll() {
        return ResponseEntity.ok(franchiseService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Franchise franchise = franchiseService.findById(id);
        return ResponseEntity.ok(franchise);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Franchise franchise) {
        franchiseService.add(franchise);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}") // PUT: localhost:8080/api/v1/students/1
    public ResponseEntity update(@RequestBody Franchise franchise, @PathVariable int id) {
        // Validates if body is correct
        if(id != franchise.getFranchiseId())
            return ResponseEntity.badRequest().build();
        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/movies")
    public ResponseEntity<Collection<Movie>> getAllMovies(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.findById(id).getMovies());
    }

    @GetMapping("{id}/characters")
    public ResponseEntity<Collection<Character>> getAllCharacters(@PathVariable int id) {
        Set<Character> characterSet = new HashSet<>();
        for(Movie i : franchiseService.findById(id).getMovies()){
            characterSet.addAll(i.getCharacters());
        }
        return ResponseEntity.ok(characterSet);
    }

    @PutMapping("{id}/movies")
    public ResponseEntity updateCharacters(@RequestBody int[] movieId, @PathVariable int id) {
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