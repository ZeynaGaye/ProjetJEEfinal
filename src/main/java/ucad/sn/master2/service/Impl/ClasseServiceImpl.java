package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.service.ClasseService;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseServiceImpl implements ClasseService {



    @Autowired
    private final ClasseRepository classeRepository;



    @Autowired
    public ClasseServiceImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public Classe sauvegarderClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public List<Classe> trouverToutesLesClasses() {
        return classeRepository.findAll();
    }

    @Override
    public Optional<Classe> trouverClasseParId(Long id) {
        return classeRepository.findById(id);
    }

    @Override
    public Classe getClasseById(Long classeId) {
        return classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée avec l'ID : " + classeId));
    }


    @Override
    public void updateClasse(Long id, Classe classe) {
        if (!classeRepository.existsById(id)) {
            throw new RuntimeException("Classe non trouvée avec l'ID : " + id);
        }
        classe.setId(id);
        classeRepository.save(classe);
    }

    @Override
    public List<Classe> findByEnseignantResponsable(Enseignant enseignant) {
        return classeRepository.findByEnseignantResponsable(enseignant);

    }

    @Override
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    @Override
    public Classe saveClasse(Classe classe) {
        return classeRepository.save(classe);
    }


}
