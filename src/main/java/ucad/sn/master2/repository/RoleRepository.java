package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.util.RoleType;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByUserAndRole(Users user, RoleType role);

    Optional<Role> findByRole(RoleType role);

    void deleteByUser(Enseignant savedEnseignant);
}
