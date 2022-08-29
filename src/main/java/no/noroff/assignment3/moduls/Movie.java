package no.noroff.assignment3.moduls;


import javax.persistence.*;
import java.util.Set;

@Entity
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

    public Set<Character> getCharacters() {
        return characters;
    }

    //
    @ManyToMany
    private Set<Character> characters;

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    // Relationship ont to many franchises/movie
    @ManyToOne
    @JoinColumn(name = "franchise" )
    private Franchise franchise;
    // one to one movie/franchise

}
