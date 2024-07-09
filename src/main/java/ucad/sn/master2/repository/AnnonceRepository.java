package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    // Ajoutez des méthodes supplémentaires de requête si nécessaire
}
