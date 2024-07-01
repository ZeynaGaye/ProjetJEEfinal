package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Administrateur;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    // Ajouter des méthodes spécifiques au besoin
}
