package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.ConsultationRessource;

@Repository
public interface ConsultationRessourceRepository extends JpaRepository<ConsultationRessource, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}
