package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.ConsultationRessource;
import ucad.sn.master2.repository.ConsultationRessourceRepository;
import ucad.sn.master2.service.ConsultationRessourceService;

import java.util.List;

@Service
public class ConsultationRessourceServiceImpl implements ConsultationRessourceService {

    private final ConsultationRessourceRepository consultationRessourceRepository;

    @Autowired
    public ConsultationRessourceServiceImpl(ConsultationRessourceRepository consultationRessourceRepository) {
        this.consultationRessourceRepository = consultationRessourceRepository;
    }

    @Override
    public ConsultationRessource sauvegarderConsultationRessource(ConsultationRessource consultationRessource) {
        return consultationRessourceRepository.save(consultationRessource);
    }

    @Override
    public List<ConsultationRessource> trouverToutesLesConsultationsRessource() {
        return consultationRessourceRepository.findAll();
    }

    // Vous pouvez ajouter d'autres méthodes d'implémentation ici si nécessaire
}
