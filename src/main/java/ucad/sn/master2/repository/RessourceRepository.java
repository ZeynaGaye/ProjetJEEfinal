package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Ressource;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}
