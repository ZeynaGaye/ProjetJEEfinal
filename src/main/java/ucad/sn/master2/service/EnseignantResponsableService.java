package ucad.sn.master2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.*;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.ModuleRepository;

import java.util.List;

@Service
public interface EnseignantResponsableService {
    void gererModules(Long classeId, List<Module> modules);
    void affecterEnseignant(Long classeId, Enseignant enseignant);
    void gererEtudiants(Long classeId, List<Users> etudiants);
    void gererAnnonces(Long classeId, List<Annonce> annonces);
}
