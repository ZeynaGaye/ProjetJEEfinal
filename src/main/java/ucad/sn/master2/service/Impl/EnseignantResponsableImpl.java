package ucad.sn.master2.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.*;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.repository.AnnonceRepository;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.ModuleRepository;
import ucad.sn.master2.service.EnseignantResponsableService;

import java.util.List;

@Service
public class EnseignantResponsableImpl implements EnseignantResponsableService {

  @Autowired
  private ClasseRepository classeRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  @Autowired
  private AnnonceRepository annonceRepository;


  @Override
  public void gererModules(Long classeId, List<Module> modules) {
    Classe classe = classeRepository.findById(classeId)
            .orElseThrow(() -> new EntityNotFoundException("Classe not found with id " + classeId));
    for (Module module : modules) {
      module.setClasse(classe);
      moduleRepository.save(module);
    }
  }


  @Override
  public void affecterEnseignant(Long classeId, Enseignant enseignant) {
    Classe classe = classeRepository.findById(classeId)
            .orElseThrow(() -> new EntityNotFoundException("Classe not found with id " + classeId));

    classe.setEnseignant(enseignant);
    classeRepository.save(classe);
  }

  @Override
  public void gererEtudiants(Long classeId, List<Etudiant> etudiants) {
    Classe classe = classeRepository.findById(classeId)
            .orElseThrow(() -> {
                return new EntityNotFoundException("Classe not found with id " + classeId);
            });
    classe.setEtudiants(etudiants);
    classeRepository.save(classe);
  }

  @Override
  public void gererAnnonces(Long classeId, List<Annonce> annonces) {
    Classe classe = classeRepository.findById(classeId)
            .orElseThrow(() -> {
                return new EntityNotFoundException("Classe not found with id " + classeId);
            });

    for (Annonce annonce : annonces) {
      annonce.setClasse(classe);
      annonceRepository.save(annonce);
    }
  }



}
