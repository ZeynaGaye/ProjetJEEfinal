package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.EnseignantResponsableClasse;

import java.util.Optional;

public interface EnseignantResponsableClasseRepository  extends JpaRepository<EnseignantResponsableClasse, Long> {


    EnseignantResponsableClasse findByEmail(String email);
}
