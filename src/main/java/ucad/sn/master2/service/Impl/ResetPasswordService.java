package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import ucad.sn.master2.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.repository.PasswordResetTokenRepository;

@Service
public class ResetPasswordService {

    private final PasswordResetTokenRepository tokenRepository;

    @Autowired
    public ResetPasswordService(PasswordResetTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Autres méthodes du service utilisant tokenRepository
}
