package ucad.sn.master2.service;

import ucad.sn.master2.model.ConsultationRessource;

import java.util.List;

public interface ConsultationRessourceService {
    ConsultationRessource sauvegarderConsultationRessource(ConsultationRessource consultationRessource);
    List<ConsultationRessource> trouverToutesLesConsultationsRessource();
    // Ajoutez d'autres méthodes si nécessaire
}
