package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Role;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.service.RoleService;
import ucad.sn.master2.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public Role createRole(@RequestBody Role role) {
        if (role.getUser() == null || role.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        Users user = userService.getUserById(role.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        role.setUser(user);
        return roleService.saveRole(role);
    }

    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/view/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/update/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
