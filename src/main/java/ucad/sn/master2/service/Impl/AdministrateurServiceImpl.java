package ucad.sn.master2.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Administrateur;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.repository.AdministrateurRepository;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EnseignantRepository;
import ucad.sn.master2.repository.RoleRepository;
import ucad.sn.master2.service.AdministrateurService;
import ucad.sn.master2.util.RoleType;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {
    private final ClasseRepository classeRepository;
    private final AdministrateurRepository administrateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnseignantRepository enseignantRepository;
    private final RoleRepository roleRepository; // Ajoutez ce champ

    private static final Logger logger = LoggerFactory.getLogger(AdministrateurServiceImpl.class);

    @Autowired
    public AdministrateurServiceImpl(
            ClasseRepository classeRepository,
            AdministrateurRepository administrateurRepository,
            PasswordEncoder passwordEncoder,
            EnseignantRepository enseignantRepository,
            RoleRepository roleRepository) { // Ajoutez roleRepository ici
        this.classeRepository = classeRepository;
        this.administrateurRepository = administrateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.enseignantRepository = enseignantRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Administrateur saveAdministrateur(Administrateur administrateur) {
        administrateur.setMotDePasse(passwordEncoder.encode(administrateur.getMotDePasse()));
        return administrateurRepository.save(administrateur);
    }

    @Override
    public Administrateur updateAdministrateur(Long id, Administrateur administrateur) {
        Optional<Administrateur> existingAdministrateur = administrateurRepository.findById(id);
        if (existingAdministrateur.isPresent()) {
            administrateur.setId(id);
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

    @Override
    public void affecterEnseignantClasse(Long enseignantId, Long classeId) {
        logger.info("Affecter enseignantId {} à classeId {}", enseignantId, classeId);

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé : " + enseignantId));
        logger.info("Enseignant trouvé : {}", enseignant);

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new IllegalArgumentException("Classe non trouvée : " + classeId));
        logger.info("Classe trouvée : {}", classe);

        // Affecter l'enseignant comme responsable de la classe
        classe.setEnseignantResponsable(enseignant);
        classeRepository.save(classe);
        logger.info("Classe mise à jour avec enseignant responsable");

        // Mettre à jour le rôle de l'enseignant
        Role roleResponsable = roleRepository.findByRole(RoleType.ENSEIGNANT_RESPONSABLE)
                .orElseThrow(() -> new IllegalArgumentException("Role ENSEIGNANT_RESPONSABLE non trouvé"));
        logger.info("Role ENSEIGNANT_RESPONSABLE trouvé : {}", roleResponsable);

        // Supprimer l'ancien rôle ENSEIGNANT
        enseignant.getRoles().removeIf(role -> role.getRole().equals(RoleType.ENSEIGNANT));
        logger.info("Ancien rôle ENSEIGNANT supprimé");

        // Ajouter le nouveau rôle ENSEIGNANT_RESPONSABLE à l'enseignant
        enseignant.getRoles().add(roleResponsable);
        enseignantRepository.save(enseignant);
        logger.info("Nouveau rôle ENSEIGNANT_RESPONSABLE ajouté à l'enseignant");

    }
}
