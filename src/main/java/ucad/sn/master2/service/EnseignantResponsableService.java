package ucad.sn.master2.service;

import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Module;

import java.util.List;

public interface EnseignantResponsableService {

    List<Classe> getAllClasses();

    void addAnnonce(Annonce annonce);

    void addModule(Module module);

//    int countResponsibleTeachers();
}
