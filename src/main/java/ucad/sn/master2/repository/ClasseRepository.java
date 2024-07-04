package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Role;

public interface ClasseRepository extends JpaRepository<Classe,Long>{
}
