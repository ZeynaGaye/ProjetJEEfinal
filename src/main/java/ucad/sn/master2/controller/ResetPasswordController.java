package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucad.sn.master2.model.PasswordResetToken;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.repository.PasswordResetTokenRepository;
import ucad.sn.master2.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ResetPasswordController {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    private  final PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    public ResetPasswordController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByToken(token);
        if (!passwordResetToken.isPresent() || passwordResetToken.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            // Gérer les cas où le token est invalide ou expiré
            model.addAttribute("error", "Le lien de réinitialisation est invalide ou a expiré.");
            return "error"; // Rediriger vers une page d'erreur appropriée
        }
        // Passer le token à la vue pour la soumission du formulaire de réinitialisation
        model.addAttribute("token", token);
        return "reset-password-form"; // Vue pour saisir le nouveau mot de passe
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByToken(token);
        if (passwordResetToken.isPresent() && passwordResetToken.get().getExpiryDate().isAfter(LocalDateTime.now())) {
            // Réinitialiser le mot de passe pour l'utilisateur associé au token
            Users user = passwordResetToken.get().getUser();
            user.setPassword(passwordEncoder.encode(password)); // Encoder le nouveau mot de passe
            userRepository.save(user);
            tokenRepository.delete(passwordResetToken.get()); // Supprimer le token après utilisation
            return "redirect:/login"; // Rediriger vers la page de connexion après réinitialisation
        } else {
            // Gérer les cas où le token est invalide ou expiré
            return "redirect:/error"; // Rediriger vers une page d'erreur appropriée
        }
    }
}
