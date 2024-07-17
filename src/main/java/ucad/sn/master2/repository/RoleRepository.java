package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.util.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByUserAndRole(Users user, RoleType role);
}
