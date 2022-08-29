package no.noroff.assignment3.services.Movie;

import no.noroff.assignment3.exceptions.FranchiseNotFoundException;
import no.noroff.assignment3.exceptions.MovieNotFoundException;
import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.repositories.FranchiseRepository;
import no.noroff.assignment3.repositories.MovieRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.logging.Logger;

@Service
public class MovieServiceImpl implements MovieService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(no.noroff.assignment3.services.Movie.MovieServiceImpl.class);
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (movieRepository.existsById(id)) {
            // Set relationships to null so we can delete without referential problems
            Movie movie = movieRepository.findById(id).get();
            movieRepository.delete(movie);
        } else
            logger.warning("No franchise exists with ID: " + id);
    }

    @Override
    public boolean exists(Integer id) {
        return movieRepository.existsById(id);
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }
}
