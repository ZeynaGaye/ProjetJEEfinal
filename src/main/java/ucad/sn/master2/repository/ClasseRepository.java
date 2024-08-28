package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    List<Classe> findByEnseignantResponsable(Enseignant enseignant);
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}
