package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Administrateur;
import ucad.sn.master2.repository.AdministrateurRepository;
import ucad.sn.master2.service.AdministrateurService;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {

    private final AdministrateurRepository administrateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdministrateurServiceImpl(AdministrateurRepository administrateurRepository, PasswordEncoder passwordEncoder) {
        this.administrateurRepository = administrateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Administrateur saveAdministrateur(Administrateur administrateur) {
        // Encoder le mot de passe avant de sauvegarder
        administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));
        return administrateurRepository.save(administrateur);
    }

    @Override
    public Administrateur updateAdministrateur(Long id, Administrateur administrateur) {
        Optional<Administrateur> existingAdministrateur = administrateurRepository.findById(id);
        if (existingAdministrateur.isPresent()) {
            administrateur.setId(id); // Assure que l'ID est correctement défini pour l'objet mis à jour
            // Encoder le mot de passe avant de sauvegarder
            administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));
            return administrateurRepository.save(administrateur);
        } else {
            throw new RuntimeException("Administrateur non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public void deleteAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }

    @Override
    public Administrateur getAdministrateurById(Long id) {
        return administrateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    @Override
    public Administrateur getByEmail(String email) {
        return administrateurRepository.findByEmail(email);
    }
}
