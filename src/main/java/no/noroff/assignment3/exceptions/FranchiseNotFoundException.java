package no.noroff.assignment3.exceptions;

public class FranchiseNotFoundException extends RuntimeException {
    public FranchiseNotFoundException(Integer id) {
        super("Franchise does not exist with ID: " + id);
    }
}
