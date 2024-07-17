package ucad.sn.master2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucad.sn.master2.model.PasswordResetToken;
import ucad.sn.master2.model.Users;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUser(Users user);
}
