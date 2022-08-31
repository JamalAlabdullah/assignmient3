package no.noroff.assignment3.moduls;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int franchiseId;
    @Column(name = "franchise_name", length = 100, nullable = false)
    private String name;
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    // Relationship ont to many franchises/movie
    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

    @JsonGetter("movies")
    public List<Integer> jsonGetMovies() {
        if(movies != null) return movies.stream().map(s -> s.getMovieId()).collect(Collectors.toList());
        return null;
    }


}
