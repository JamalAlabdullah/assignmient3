package no.noroff.assignment3.controllers;

import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.services.Franchise.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
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
}