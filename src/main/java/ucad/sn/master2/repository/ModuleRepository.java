package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ucad.sn.master2.model.Module;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<java.lang.Module> findByClasseId(Long classeId);
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}
