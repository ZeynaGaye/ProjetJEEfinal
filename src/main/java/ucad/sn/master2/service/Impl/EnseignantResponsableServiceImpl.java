package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.*;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.repository.AnnonceRepository;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EnseignantResponsableClasseRepository;
import ucad.sn.master2.repository.ModuleRepository;
import ucad.sn.master2.service.EnseignantResponsableService;
import ucad.sn.master2.util.RoleType;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;
import ucad.sn.master2.model.EnseignantResponsableClasse;
import ucad.sn.master2.model.PasswordResetToken;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.repository.*;
import ucad.sn.master2.service.EmailService;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnseignantResponsableServiceImpl implements EnseignantResponsableService {

  @Autowired
  private ClasseRepository classeRepository;

  @Autowired
  private AnnonceRepository annonceRepository;

  @Autowired
  private ModuleRepository moduleRepository;
  @Autowired
  private EnseignantResponsableClasseRepository enseignantResponsableClasseRepository;
  private final PasswordResetTokenRepository tokenRepository;
  private final EmailService emailService;

  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  @Override
  public List<Classe> getAllClasses() {
    return classeRepository.findAll();
  }

  @Override
  public void addAnnonce(Annonce annonce) {
    annonceRepository.save(annonce);
  }

  @Override
  public void addModule(Module module) {
    moduleRepository.save(module);
  }


  @Override
  public void addEnseignantResponsable(EnseignantResponsableClasse enseignantResponsable) {
    enseignantResponsableClasseRepository.save(enseignantResponsable);
  }

  @Override
  public List<EnseignantResponsableClasse> getAllEnseignantsResponsables() {
    return null;
  }


  @Autowired
  public EnseignantResponsableServiceImpl(PasswordResetTokenRepository tokenRepository, EmailService emailService,
                                          EnseignantResponsableClasseRepository enseignantResponsableClasseRepository, PasswordEncoder passwordEncoder,
                                          ClasseRepository classeRepository, RoleRepository roleRepository) {
    this.tokenRepository = tokenRepository;
    this.emailService = emailService;
    this.enseignantResponsableClasseRepository = enseignantResponsableClasseRepository;
    this.passwordEncoder = passwordEncoder;
    this.classeRepository = classeRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  @Transactional
  public EnseignantResponsableClasse ajouterEnseignantResponsable(EnseignantResponsableClasse enseignantResponsable) {
    enseignantResponsable.setMotDePasse(passwordEncoder.encode(enseignantResponsable.getMotDePasse()));
    EnseignantResponsableClasse savedEnseignantResponsable = enseignantResponsableClasseRepository.save(enseignantResponsable);

    Role existingRole = roleRepository.findByUserAndRole(enseignantResponsable, RoleType.ENSEIGNANT_RESPONSABLE);

    if (existingRole == null) {
      Role roleResponsable = new Role();
      roleResponsable.setRole(RoleType.ENSEIGNANT_RESPONSABLE);
      roleResponsable.setUser(enseignantResponsable);
      roleRepository.save(roleResponsable);
    }

    LocalDateTime expiryDate = LocalDateTime.now().plus(2, ChronoUnit.DAYS);
    String token = UUID.randomUUID().toString();
    PasswordResetToken passwordResetToken = new PasswordResetToken(token, savedEnseignantResponsable, expiryDate);
    tokenRepository.save(passwordResetToken);

    String resetPasswordLink = "http://localhost:8090/reset-password?token=" + token;
    emailService.sendPasswordResetEmailEnseignantResponsable(savedEnseignantResponsable, resetPasswordLink);

    return savedEnseignantResponsable;
  }

  @Override
  public List<EnseignantResponsableClasse> listeEnseignantsResponsables() {
    return enseignantResponsableClasseRepository.findAll();
  }

  @Override
  public EnseignantResponsableClasse updateEnseignantResponsable(Long id, EnseignantResponsableClasse enseignantResponsable) {
    Optional<EnseignantResponsableClasse> existingEnseignantResponsable = enseignantResponsableClasseRepository.findById(id);
    if (existingEnseignantResponsable.isPresent()) {
      enseignantResponsable.setId(id);
      return enseignantResponsableClasseRepository.save(enseignantResponsable);
    } else {
      throw new RuntimeException("Enseignant Responsable non trouvé avec l'ID : " + id);
    }
  }

  @Override
  public void deleteEnseignantResponsable(Long id) {
    enseignantResponsableClasseRepository.deleteById(id);
  }

  @Override
  public EnseignantResponsableClasse getEnseignantResponsableById(Long id) {
    return enseignantResponsableClasseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Enseignant Responsable non trouvé avec l'ID : " + id));
  }

  @Override
  public EnseignantResponsableClasse findByEmail(String email) {
    return enseignantResponsableClasseRepository.findByEmail(email);
  }

}
