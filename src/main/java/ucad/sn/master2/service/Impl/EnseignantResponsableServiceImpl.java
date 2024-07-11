package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.repository.AnnonceRepository;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.ModuleRepository;
import ucad.sn.master2.service.EnseignantResponsableService;

import java.util.List;

@Service
public class EnseignantResponsableServiceImpl implements EnseignantResponsableService {

  @Autowired
  private ClasseRepository classeRepository;

  @Autowired
  private AnnonceRepository annonceRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  @Override
  public List<Classe> getAllClasses() {
    return classeRepository.findAll();
  }

  @Override
  public void addAnnonce(Annonce annonce) {
    annonceRepository.save(annonce);
  }

  @Override
  public void addModule(Module module) {
    moduleRepository.save(module);
  }
}
