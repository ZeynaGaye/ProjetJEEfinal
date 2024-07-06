package ucad.sn.master2.service.Impl;

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
  @Override




  public void gererModules(Long classeId, List<Module> modules) {

  }

  @Override
  public void affecterEnseignant(Long classeId, Enseignant enseignant) {

  }

  @Override
  public void gererEtudiants(Long classeId, List<Users> etudiants) {

  }

  @Override
  public void gererAnnonces(Long classeId, List<Annonce> annonces) {

  }



}
