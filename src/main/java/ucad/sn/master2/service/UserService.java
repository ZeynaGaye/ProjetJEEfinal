package ucad.sn.master2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Users;

import java.util.List;
import java.util.Optional;
public interface UserService extends UserDetailsService {
    Users saveUsers(Users user);
    List<Users> getAllUsers();
    Optional<Users> getUserById(Long id);
    Users updateUser(Long id, Users user);
    void deleteUser(Long id);

    void saveUser(Users userForm);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
