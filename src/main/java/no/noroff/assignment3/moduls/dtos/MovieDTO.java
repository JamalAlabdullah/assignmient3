package no.noroff.assignment3.moduls.dtos;
import lombok.Data;
import no.noroff.assignment3.moduls.Character;

import java.util.Set;

@Data
public class MovieDTO {
    private int id;
    private String director;
    private String genre;
    private String title;
    private String picture_url;
    private String trailerl_ink;
    private int franchise_id;
    private Set<Character> characters;

}
