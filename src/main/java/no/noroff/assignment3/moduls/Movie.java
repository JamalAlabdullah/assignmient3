package no.noroff.assignment3.moduls;


import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @Column(name = "movie_Title", length = 100, nullable = false)
    private String movieTitle;
    @Column(name = "genre", length = 100, nullable = false)
    private String genre;
    @Column(name = "director", length = 100, nullable = false)
    private String director;
    @Column(name = "picture_Url", length = 100, nullable = false)
    private String pictureUrl;
    @Column(name = "trailerL_ink", length = 100, nullable = false)
    private String trailerLink;

    //
    @ManyToMany
    private Set<Character> characters;

    @JsonGetter("characters")
    public List<Integer> jsonGetCharacters() {
        if(characters != null)
            return characters.stream().map(s -> s.getCharacterId())
                    .collect(Collectors.toList());
        return null;
    }

    // Relationship ont to many franchises/movie
    @ManyToOne
    @JoinColumn(name = "franchise" )
    private Franchise franchise;
    // one to one movie/franchise
    @JsonGetter("franchise")
    public Integer jsonGetFranchise() {
        if(franchise != null)
            return franchise.getFranchiseId();
        return null;
    }

}
