package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Enseignant;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Enseignant findByEmail(String email);
    // Ajouter des méthodes spécifiques au besoin
}
