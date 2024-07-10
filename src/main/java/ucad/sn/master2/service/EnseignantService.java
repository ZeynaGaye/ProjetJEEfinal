package ucad.sn.master2.service;

import ucad.sn.master2.model.Enseignant;

import java.util.List;

public interface EnseignantService {

    static void affecterEnseignantClasse(Long enseignantId, Long classeId) {
    }

    Enseignant saveEnseignant(Enseignant enseignant);

    Enseignant updateEnseignant(Long id, Enseignant enseignant);

    void deleteEnseignant(Long id);

    Enseignant getEnseignantById(Long id);

    List<Enseignant> getAllEnseignants();


    //     static void affecterEnseignantClasse(Long enseignantId, Long classeId){
//         Enseignant enseignant = EnseignantRepository.findById(enseignantId).orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé"));
//         Classe classe = ClasseRepository.findById(classeId).orElseThrow(() -> new IllegalArgumentException("Classe non trouvée"));
//         classe.getEnseignant().add(enseignant);
//         enseignant.getClasses().add(classe); // Mise à jour bidirectionnelle
//         ClasseRepository.save(classe);
//         EnseignantRepository.save(enseignant);
//     }
}

