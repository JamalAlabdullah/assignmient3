package no.noroff.assignment3.moduls;


import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterId;
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @Column(name = "alias", length = 100, nullable = false)
    private String alias;
    @Column(name = "gender", length = 100, nullable = false)
    private String gender;
    @Column(name = "picture_url", length = 100, nullable = false)
    private String pictureUrl;

    @JsonGetter("movies")
    public List<Integer> jsonGetMovies() {
        if(movies != null)
            return movies.stream().map(s -> s.getMovieId())
                    .collect(Collectors.toList());
        return null;
    }

    // Relationship/ many To many
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;

}
