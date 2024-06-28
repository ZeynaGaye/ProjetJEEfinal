package ucad.sn.master2.service;

import ucad.sn.master2.model.Role;
import ucad.sn.master2.model.Users;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Role updateRole(Long id, Role role);
    void deleteRole(Long id);
}
