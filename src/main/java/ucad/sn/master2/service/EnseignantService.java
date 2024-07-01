package ucad.sn.master2.service;

import ucad.sn.master2.model.Enseignant;

import java.util.List;

public interface EnseignantService {

    Enseignant saveEnseignant(Enseignant enseignant);

    Enseignant updateEnseignant(Long id, Enseignant enseignant);

    void deleteEnseignant(Long id);

    Enseignant getEnseignantById(Long id);

    List<Enseignant> getAllEnseignants();
}
