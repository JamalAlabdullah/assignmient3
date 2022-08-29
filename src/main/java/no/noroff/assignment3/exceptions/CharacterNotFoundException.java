package no.noroff.assignment3.exceptions;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(Integer id) {
        super("Character does not exist with ID: " + id);
    }
}
