package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Module;

public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
}
