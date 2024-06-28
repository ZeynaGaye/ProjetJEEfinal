package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.repository.RoleRepository;
import ucad.sn.master2.repository.UserRepository;
import ucad.sn.master2.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Role saveRole(Role role) {
        Users user = userRepository.findById(role.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        role.setUser(user);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setRole(role.getRole());
                    existingRole.setUser(role.getUser());
                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
