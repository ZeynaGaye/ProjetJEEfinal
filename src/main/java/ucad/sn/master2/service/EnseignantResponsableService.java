package ucad.sn.master2.service;

import org.springframework.transaction.annotation.Transactional;
import ucad.sn.master2.model.*;
import ucad.sn.master2.model.Module;

import java.util.List;

public interface EnseignantResponsableService {

    List<Classe> getAllClasses();

    void addAnnonce(Annonce annonce);

    void addModule(Module module);

    void addEnseignantResponsable(EnseignantResponsableClasse enseignantResponsable);

    List<EnseignantResponsableClasse> getAllEnseignantsResponsables();

    @Transactional
    EnseignantResponsableClasse ajouterEnseignantResponsable(EnseignantResponsableClasse enseignantResponsable);

    List<EnseignantResponsableClasse> listeEnseignantsResponsables();

    EnseignantResponsableClasse updateEnseignantResponsable(Long id, EnseignantResponsableClasse enseignantResponsable);

    void deleteEnseignantResponsable(Long id);

    EnseignantResponsableClasse getEnseignantResponsableById(Long id);

    EnseignantResponsableClasse findByEmail(String email);


//    int countResponsibleTeachers();

 
}
