package ucad.sn.master2.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ucad.sn.master2.model.Users;

import java.util.List;
import java.util.Optional;
public interface UserService {
    Users saveUsers(Users user);
    List<Users> getAllUsers();
    Optional<Users> getUserById(Long id);
    Users updateUser(Long id, Users user);
    void deleteUser(Long id);

    void saveUser(Users userForm);

    Users loadUserByUsername(String email) throws UsernameNotFoundException;
}
