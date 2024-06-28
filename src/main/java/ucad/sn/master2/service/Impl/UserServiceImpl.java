package ucad.sn.master2.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.repository.UserRepository;
import ucad.sn.master2.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users saveUsers(Users user) {
        logger.info("Enregistrement de l'utilisateur  avec succes: {}", user);
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
                    existingUser.setMotDePasse(user.getMotDePasse());
                    logger.info("Mise à jour de l'utilisateur avec succes : {}", existingUser);
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        logger.info("Suppression de l'utilisateur avec ID : {}", id);
    }
}
