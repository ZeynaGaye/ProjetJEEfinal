package ucad.sn.master2.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.PasswordResetToken;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.repository.*;
import ucad.sn.master2.service.EmailService;
import ucad.sn.master2.service.EtudiantService;
import ucad.sn.master2.util.RoleType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EtudiantRepository;
import ucad.sn.master2.repository.RoleRepository;
import ucad.sn.master2.repository.UserRepository;


@Service
public class EtudiantServiceImpl implements EtudiantService {
    private final PasswordEncoder passwordEncoder;
    private final EtudiantRepository etudiantRepository;
    private final RoleRepository roleRepository;

    private  final PasswordResetTokenRepository tokenRepository;

    private final ClasseRepository classeRepository;
    private final UserRepository userRepository;

    private EmailService emailService;

    @Autowired
    public EtudiantServiceImpl(PasswordEncoder passwordEncoder, EtudiantRepository etudiantRepository, RoleRepository roleRepository, PasswordResetTokenRepository tokenRepository, ClasseRepository classeRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.etudiantRepository = etudiantRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.classeRepository = classeRepository;
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

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
        // Calculer la date d'expiration du token (2 jours à partir de maintenant)
        LocalDateTime expiryDate = LocalDateTime.now().plus(2, ChronoUnit.DAYS);

        // Envoyer un email de réinitialisation de mot de passe
        String token =  UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, etudiant, expiryDate);
        tokenRepository.save(passwordResetToken);
        String resetPasswordLink = "http://localhost:8080/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(etudiant, resetPasswordLink);

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

    @Override
    public Etudiant findByEmail(String email) {
        return (Etudiant) etudiantRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec l'email : " + email));
    }




}



