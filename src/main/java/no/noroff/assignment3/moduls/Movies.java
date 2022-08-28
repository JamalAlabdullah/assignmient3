package no.noroff.assignment3.moduls;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    @Column(name = "movi_Title", length = 100, nullable = false)
    private String moviTitle;
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
    private Set<Characters> characters;

    // Relationship ont to many franchises/movie
    @ManyToOne
    @JoinColumn(name = "franchises" )
    private Franchises franchises;
    // one to one movie/franchise
    @OneToOne
    @JoinColumn(name="franchiseId")
    private Franchises franchisess;



}
