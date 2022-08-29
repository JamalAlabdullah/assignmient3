package no.noroff.assignment3.moduls;


import javax.persistence.*;
import java.util.Set;

@Entity
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

    public Set<Movie> getMovies() {
        return movies;
    }

    // Relationship/ many To many
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;

}
