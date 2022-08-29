package no.noroff.assignment3.moduls;

import javax.persistence.*;
import java.util.Set;


@Entity
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


}
