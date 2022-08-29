package no.noroff.assignment3.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Integer id) {
        super("Movie does not exist with ID: " + id);
    }
}
