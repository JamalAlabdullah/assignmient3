package no.noroff.assignment3.moduls;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Franchises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int franchiseId;
    @Column(name = "franchis_name", length = 100, nullable = false)
    private String name;
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    // Relationship ont to many franchises/movie
    @OneToMany(mappedBy = "franchises")
    private Set<Movies> movies;

    // one to one movie/franchise
    @OneToOne(mappedBy = "franchiseId")
    private Movies moviess;



}
