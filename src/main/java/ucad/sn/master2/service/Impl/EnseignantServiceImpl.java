package ucad.sn.master2.service.Impl;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.PasswordResetToken;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.repository.*;
import ucad.sn.master2.service.EmailService;
import ucad.sn.master2.service.EnseignantService;
import ucad.sn.master2.util.RoleType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnseignantServiceImpl implements EnseignantService {

    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final EnseignantRepository enseignantRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClasseRepository classeRepository;
    private final RoleRepository roleRepository;
    private final AnnonceRepository annonceRepository;
    @Autowired
    public EnseignantServiceImpl(PasswordResetTokenRepository tokenRepository, EmailService emailService,
                                 EnseignantRepository enseignantRepository, PasswordEncoder passwordEncoder,
                                 ClasseRepository classeRepository, RoleRepository roleRepository, AnnonceRepository annonceRepository) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.enseignantRepository = enseignantRepository;
        this.passwordEncoder = passwordEncoder;
        this.classeRepository = classeRepository;
        this.roleRepository = roleRepository;

        this.annonceRepository = annonceRepository;
    }

    @Override
    @Transactional
    public Enseignant ajouterEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    @Override
    public List<Enseignant> listeEnseignants() {
        return enseignantRepository.findAll();
    }



    @Override
    @Transactional
    public Enseignant saveEnseignant(Enseignant enseignant, RoleType roleType) {
        enseignant.setMotDePasse(passwordEncoder.encode(enseignant.getMotDePasse()));
        Enseignant savedEnseignant = enseignantRepository.save(enseignant);
        // Vérifier si l'utilisateur a déjà le rôle "ETUDIANT"
       // Role existingRole = roleRepository.findByUserAndRole(enseignant, RoleType.ENSEIGNANT);


    //    if (existingRole == null) {
            // Création du rôle d'étudiant uniquement s'il n'existe pas déjà
            //Role roleEnseignant = new Role();
           // roleEnseignant.setRole(RoleType.ENSEIGNANT);
           // roleEnseignant.setUser(enseignant); // Associer le rôle à l'étudiant sauvegardé
           // roleRepository.save(roleEnseignant);
      //  }

        // Assurez-vous que la liste des rôles est initialisée
        if (savedEnseignant.getRoles() == null) {
            savedEnseignant.setRoles(new ArrayList<>());
        }

        // Créer et ajouter le rôle approprié si nécessaire
        Role role = roleRepository.findByUserAndRole(savedEnseignant, roleType);

        if (role == null) {
            role = new Role();
            role.setRole(roleType);
            role.setUser(savedEnseignant);

            // Sauvegarder le rôle dans la base de données
            roleRepository.save(role);

            // Ajouter le rôle à la liste des rôles de l'enseignant
            savedEnseignant.getRoles().add(role);

            // Sauvegarder l'enseignant avec le rôle mis à jour
            enseignantRepository.save(savedEnseignant);
        }



        LocalDateTime expiryDate = LocalDateTime.now().plus(2, ChronoUnit.DAYS);
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, savedEnseignant, expiryDate);
        tokenRepository.save(passwordResetToken);

        String resetPasswordLink = "http://localhost:8090/reset-password?token=" + token;
        emailService.sendPasswordResetEmailEnseignant(savedEnseignant, resetPasswordLink);

        // Message de succès
        String successMessage = "Un email de réinitialisation vous a été envoyé à " + savedEnseignant.getEmail();
        System.out.println(successMessage); // Vous pouvez aussi le logger ou le retourner comme résultat de la méthode

        return savedEnseignant;
    }

    @Override
    public Enseignant updateEnseignant(Long id, Enseignant enseignant) {
        Optional<Enseignant> existingEnseignant = enseignantRepository.findById(id);
        if (existingEnseignant.isPresent()) {
            enseignant.setId(id);
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

    @Override
    public Enseignant findByEmail(String email) {
        return enseignantRepository.findByEmail(email);
    }


    // @Override
    @Override
    public Enseignant getEnseignantByEmail(String email) {
        Enseignant enseignant = enseignantRepository.findByEmail(email);
        if (enseignant != null) {
            System.out.println("Enseignant trouvé: " + enseignant.getNom());
            System.out.println("Nombre d'annonces: " + enseignant.getAnnonces().size());
        }
        return enseignant;
    }
    @Override
    public void creerAnnonce(Annonce annonce, String emailEnseignant) {
        Enseignant enseignant = enseignantRepository.findByEmail(emailEnseignant);
        if (enseignant == null) {
            throw new IllegalArgumentException("Enseignant not found with email: " + emailEnseignant);
        }
        annonce.setEnseignant(enseignant);
        enseignant.getAnnonces().add(annonce);
        annonceRepository.save(annonce);
    }
    @Override
    public void creerAnnonce(Annonce annonce) {

    }

}
