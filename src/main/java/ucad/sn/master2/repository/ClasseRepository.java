package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}
