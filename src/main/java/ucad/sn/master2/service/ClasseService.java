package ucad.sn.master2.service;

import ucad.sn.master2.model.Classe;

import java.util.List;
import java.util.Optional;

public interface ClasseService {
    Classe sauvegarderClasse(Classe classe);
    List<Classe> trouverToutesLesClasses();
    Optional<Classe> trouverClasseParId(Long id);

    //long countClasses();
}
