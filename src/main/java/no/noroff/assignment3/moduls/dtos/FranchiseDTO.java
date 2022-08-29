package no.noroff.assignment3.moduls.dtos;
import lombok.Data;
import no.noroff.assignment3.moduls.Movie;

import java.util.Set;

@Data
public class FranchiseDTO {
    private int id;
    private String description;
    private String name;
    private Set<Movie> movies;
}
