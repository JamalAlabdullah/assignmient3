package no.noroff.assignment3.moduls.dtos;

import lombok.Data;
import no.noroff.assignment3.moduls.Movie;

import java.util.Set;

@Data
public class CharacterDTO {
    private int id;
    private String alias;
    private String fullName;
    private String gender;
    private String picture_url;
    private Set<Movie> movies;
}
