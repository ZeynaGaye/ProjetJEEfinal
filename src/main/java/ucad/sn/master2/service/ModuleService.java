package ucad.sn.master2.service;

import ucad.sn.master2.model.Module;

import java.util.List;

public interface ModuleService {

    // Méthode pour récupérer tous les modules
    List<Module> getAllModules();

    // Méthode pour récupérer un module par son identifiant
    Module getModuleById(Long id);

    // Méthode pour ajouter un nouveau module
    Module addModule(Module module);

    // Méthode pour mettre à jour un module existant
    Module updateModule(Long id, Module module);

    // Méthode pour supprimer un module par son identifiant
    void deleteModule(Long id);
}
