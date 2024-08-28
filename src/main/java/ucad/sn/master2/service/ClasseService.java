package ucad.sn.master2.service;

import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;

import java.util.List;
import java.util.Optional;

public interface ClasseService {
    Classe sauvegarderClasse(Classe classe);
    List<Classe> trouverToutesLesClasses();
    Optional<Classe> trouverClasseParId(Long id);

    Classe getClasseById(Long classeId);


    void updateClasse(Long id, Classe classe);

    List<Classe> findByEnseignantResponsable(Enseignant enseignant);

    List<Classe> getAllClasses();

    Classe saveClasse(Classe classe);
}
