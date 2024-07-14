package ucad.sn.master2.service;

import ucad.sn.master2.model.Administrateur;

import java.util.List;

public interface AdministrateurService {

    Administrateur saveAdministrateur(Administrateur administrateur);

    Administrateur updateAdministrateur(Long id, Administrateur administrateur);

    void deleteAdministrateur(Long id);

    Administrateur getAdministrateurById(Long id);

    List<Administrateur> getAllAdministrateurs();

    Administrateur getByEmail(String email);
}
