package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.service.ClasseService;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseServiceImpl implements ClasseService {

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

    // Vous pouvez ajouter d'autres méthodes d'implémentation ici si nécessaire
}
