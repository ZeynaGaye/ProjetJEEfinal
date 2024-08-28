package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Ressource;

import java.util.List;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {


    List<Ressource> findByClasseId(Long id);
    @Query("SELECT r FROM Ressource r JOIN r.module m WHERE m.enseignant = :enseignant")
    List<Ressource> findByEnseignant(@Param("enseignant") Enseignant enseignant);


    List<Ressource> findByModuleId(Long moduleId);

}
