package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Ressource;

import java.util.List;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {


    List<Ressource> findByClasseId(Long id);
}
