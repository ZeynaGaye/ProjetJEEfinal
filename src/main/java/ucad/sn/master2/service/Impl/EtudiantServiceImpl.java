package ucad.sn.master2.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EtudiantRepository;
import ucad.sn.master2.repository.RoleRepository;
import ucad.sn.master2.repository.UserRepository;
import ucad.sn.master2.service.EtudiantService;
import ucad.sn.master2.util.RoleType;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    private final PasswordEncoder passwordEncoder;

    private final EtudiantRepository etudiantRepository;
    private final RoleRepository roleRepository;
    private final ClasseRepository classeRepository;
    private final UserRepository userRepository;
    @Autowired
    public EtudiantServiceImpl(PasswordEncoder passwordEncoder, EtudiantRepository etudiantRepository, RoleRepository roleRepository, ClasseRepository claseRepository, ClasseRepository classeRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.etudiantRepository = etudiantRepository;
        this.roleRepository = roleRepository;

        this.classeRepository = classeRepository;
        this.userRepository = userRepository;
    }

    // @Override
    //public Etudiant saveEtudiant(Etudiant etudiant) {
    //  etudiant.setMotDePasse(passwordEncoder.encode(etudiant.getMotDePasse()));
    //  return etudiantRepository.save(etudiant);
    // }
    @Override
    @Transactional
    public Etudiant saveEtudiant(Etudiant etudiant) {
        // Encoder le mot de passe
        etudiant.setMotDePasse(passwordEncoder.encode(etudiant.getMotDePasse()));

        // Récupérer l'ID de la classe à partir de l'étudiant
        Long classeId = etudiant.getClasse().getId();

        // Récupérer l'objet Classe à partir de l'ID
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée avec l'ID : " + classeId));

        // Associer l'étudiant à la classe
        etudiant.setClasse(classe);

        // Sauvegarder l'étudiant pour obtenir son ID
        etudiant = etudiantRepository.save(etudiant);

        // Vérifier si l'utilisateur a déjà le rôle "ETUDIANT"
        Role existingRole = roleRepository.findByUserAndRole(etudiant, RoleType.ETUDIANT);

        if (existingRole == null) {
            // Création du rôle d'étudiant uniquement s'il n'existe pas déjà
            Role roleEtudiant = new Role();
            roleEtudiant.setRole(RoleType.ETUDIANT);
            roleEtudiant.setUser(etudiant); // Associer le rôle à l'étudiant sauvegardé
            roleRepository.save(roleEtudiant);
        }

        return etudiant;
    }



    @Override
    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        Optional<Etudiant> existingEtudiant = etudiantRepository.findById(id);
        if (existingEtudiant.isPresent()) {
            etudiant.setId(id); // Assure que l'ID est correctement défini pour l'objet mis à jour
            return etudiantRepository.save(etudiant);
        } else {
            throw new RuntimeException("Etudiant non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
}



