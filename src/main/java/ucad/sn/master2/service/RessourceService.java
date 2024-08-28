package ucad.sn.master2.service;

import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Ressource;

import java.util.List;

public interface RessourceService {

    // Méthode pour récupérer toutes les ressources
    List<Ressource> getAllRessources();

    // Méthode pour récupérer une ressource par son identifiant
    Ressource getRessourceById(Long id);

    // Méthode pour ajouter une nouvelle ressource
    Ressource addRessource(Ressource ressource);

    // Méthode pour mettre à jour une ressource existante
    Ressource updateRessource(Long id, Ressource ressource);

    // Méthode pour supprimer une ressource par son identifiant
    void deleteRessource(Long id);

    List<Ressource> getRessourcesByClasseId(Long id);

    List<Ressource> getRessourcesByEnseignant(Enseignant enseignant);

    List<Ressource> getRessourcesForModule(Long moduleId);
}
