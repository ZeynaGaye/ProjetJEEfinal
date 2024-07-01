package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Ressource;
import ucad.sn.master2.repository.RessourceRepository;
import ucad.sn.master2.service.RessourceService;

import java.util.List;

@Service
public class RessourceServiceImpl implements RessourceService {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Override
    public List<Ressource> getAllRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    public Ressource getRessourceById(Long id) {
        return ressourceRepository.findById(id).orElse(null);
    }

    @Override
    public Ressource addRessource(Ressource ressource) {
        // Assurez-vous que l'ID est nul pour éviter les conflits lors de la sauvegarde
        ressource.setId(null);
        return ressourceRepository.save(ressource);
    }

    @Override
    public Ressource updateRessource(Long id, Ressource ressource) {
        Ressource existingRessource = ressourceRepository.findById(id).orElse(null);
        if (existingRessource != null) {
            // Mettre à jour les champs de la ressource existante avec les nouvelles valeurs
            existingRessource.setNom(ressource.getNom());
            existingRessource.setDescription(ressource.getDescription());
            // Ajoutez d'autres champs à mettre à jour selon votre modèle
            return ressourceRepository.save(existingRessource);
        }
        return null;
    }

    @Override
    public void deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
    }
}
