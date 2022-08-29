package no.noroff.assignment3.repositories;

import no.noroff.assignment3.moduls.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository  extends JpaRepository<Character, Integer> {
}
