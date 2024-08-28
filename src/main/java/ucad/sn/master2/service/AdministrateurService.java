package ucad.sn.master2.service;

import jakarta.transaction.Transactional;
import ucad.sn.master2.model.Administrateur;

import java.util.List;

public interface AdministrateurService {

    Administrateur saveAdministrateur(Administrateur administrateur);

    Administrateur updateAdministrateur(Long id, Administrateur administrateur);

    void deleteAdministrateur(Long id);

    Administrateur getAdministrateurById(Long id);

    List<Administrateur> getAllAdministrateurs();

    Administrateur getByEmail(String email);

    void affecterEnseignantClasse(Long enseignantId, Long classeId);



}
