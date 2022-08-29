package no.noroff.assignment3.repositories;

import jdk.jfr.Registered;
import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.moduls.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository  extends JpaRepository<Franchise, Integer> {
}
