package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Administrateur;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.repository.UserRepository;
import ucad.sn.master2.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users saveUsers(Users user) {
        user.setMotDePasse(new BCryptPasswordEncoder().encode(user.getMotDePasse()));
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Users updateUser(Long id, Users user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNom(user.getNom());
                    existingUser.setPrenom(user.getPrenom());
                    existingUser.setGenre(user.getGenre());
                    existingUser.setAdresse(user.getAdresse());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setMotDePasse(new BCryptPasswordEncoder().encode(user.getMotDePasse()));
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUser(Users userForm) {
        if (userForm instanceof Etudiant || userForm instanceof Enseignant || userForm instanceof Administrateur) {
            userRepository.save(userForm);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec email: " + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getMotDePasse(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                        .collect(Collectors.toList()));
    }
}
