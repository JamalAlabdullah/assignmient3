package no.noroff.assignment3.services.Character;

import no.noroff.assignment3.exceptions.CharacterNotFoundException;
import no.noroff.assignment3.moduls.Character;
import no.noroff.assignment3.repositories.CharacterRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.logging.Logger;

@Service
public class CharacterServiceImpl implements CharacterService{

    private final Logger logger = (Logger) LoggerFactory.getLogger(CharacterServiceImpl.class);
    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if(characterRepository.existsById(id)) {
            // Set relationships to null so we can delete without referential problems
            Character character = characterRepository.findById(id).get();
            characterRepository.delete(character);
        }
        else
            logger.warning("No character exists with ID: " + id);
    }

    @Override
    public boolean exists(Integer id) {
        return characterRepository.existsById(id);
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }

}
