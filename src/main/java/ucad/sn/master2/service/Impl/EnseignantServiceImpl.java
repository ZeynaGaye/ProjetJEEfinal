package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.repository.EnseignantRepository;
import ucad.sn.master2.service.EnseignantService;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantRepository enseignantRepository;

    @Autowired
    public EnseignantServiceImpl(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    @Override
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Enseignant updateEnseignant(Long id, Enseignant enseignant) {
        Optional<Enseignant> existingEnseignant = enseignantRepository.findById(id);
        if (existingEnseignant.isPresent()) {
            enseignant.setId(id); // Assure que l'ID est correctement défini pour l'objet mis à jour
            return enseignantRepository.save(enseignant);
        } else {
            throw new RuntimeException("Enseignant non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    @Override
    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }
}
