package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.repository.ModuleRepository;
import ucad.sn.master2.service.ModuleService;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    @Override
    public Module addModule(Module module) {
        // Assurez-vous que l'ID est nul pour éviter les conflits lors de la sauvegarde
        module.setId(null);
        return moduleRepository.save(module);
    }

    @Override
    public Module updateModule(Long id, Module module) {
        Module existingModule = moduleRepository.findById(id).orElse(null);
        if (existingModule != null) {
            // Mettre à jour les champs du module existant avec les nouvelles valeurs
            existingModule.setNom(module.getNom());
            existingModule.setDescription(module.getDescription());
            // Ajoutez d'autres champs à mettre à jour selon votre modèle
            return moduleRepository.save(existingModule);
        }
        return null;
    }

    @Override
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
}
