package no.noroff.assignment3.mappers;

import no.noroff.assignment3.moduls.Movie;
import no.noroff.assignment3.moduls.dtos.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDTO movieToMovieDTO(Movie movie);

}
