package no.noroff.assignment3.services.Franchise;

import no.noroff.assignment3.exceptions.CharacterNotFoundException;
import no.noroff.assignment3.exceptions.FranchiseNotFoundException;
import no.noroff.assignment3.moduls.Character;
import no.noroff.assignment3.moduls.Franchise;
import no.noroff.assignment3.repositories.CharacterRepository;
import no.noroff.assignment3.repositories.FranchiseRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.logging.Logger;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(no.noroff.assignment3.services.Franchise.FranchiseServiceImpl.class);
    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (franchiseRepository.existsById(id)) {
            // Set relationships to null so we can delete without referential problems
            Franchise franchise = franchiseRepository.findById(id).get();
            franchise.getMovies().forEach(s -> s.setFranchise(null));
            franchiseRepository.delete(franchise);
        } else
            logger.warning("No franchise exists with ID: " + id);
    }

    @Override
    public boolean exists(Integer id) {
        return franchiseRepository.existsById(id);
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id)
                .orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }
}
