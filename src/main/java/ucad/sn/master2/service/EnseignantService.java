package ucad.sn.master2.service;

import jakarta.transaction.Transactional;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.util.RoleType;

import java.util.List;

public interface EnseignantService {


    static void affecterEnseignantClasse(Long enseignantId, Long classeId) {
    }

    @Transactional
    Enseignant ajouterEnseignant(Enseignant enseignant);

    List<Enseignant> listeEnseignants();

    Enseignant saveEnseignant(Enseignant enseignant, RoleType roleType);


    Enseignant getEnseignantByEmail(String email);
    Enseignant updateEnseignant(Long id, Enseignant enseignant);

    void deleteEnseignant(Long id);

    Enseignant getEnseignantById(Long id);

    List<Enseignant> getAllEnseignants();

    Enseignant findByEmail(String email);
    void creerAnnonce(Annonce annonce, String name);


    void creerAnnonce(Annonce annonce);

    //@Transactional
   // void affecterEnseignantClasse(Long enseignantId, Long classeId);


    //     static void affecterEnseignantClasse(Long enseignantId, Long classeId){
//         Enseignant enseignant = EnseignantRepository.findById(enseignantId).orElseThrow(() -> new IllegalArgumentException("Enseignant non trouvé"));
//         Classe classe = ClasseRepository.findById(classeId).orElseThrow(() -> new IllegalArgumentException("Classe non trouvée"));
//         classe.getEnseignant().add(enseignant);
//         enseignant.getClasses().add(classe); // Mise à jour bidirectionnelle
//         ClasseRepository.save(classe);
//         EnseignantRepository.save(enseignant);
//     }




}

