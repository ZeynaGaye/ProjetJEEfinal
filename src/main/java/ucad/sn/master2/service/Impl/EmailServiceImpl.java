package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.EnseignantResponsableClasse;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendPasswordResetEmail(Etudiant etudiant, String resetPasswordLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(etudiant.getEmail());
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Pour réinitialiser votre mot de passe, veuillez suivre ce lien : " + resetPasswordLink);

        emailSender.send(message);
    }

    public void sendPasswordResetEmailEnseignant(Enseignant enseignant, String resetPasswordLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(enseignant.getEmail());
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Pour réinitialiser votre mot de passe, veuillez suivre ce lien : " + resetPasswordLink);

        emailSender.send(message);
    }

    @Override
    public void sendPasswordResetEmailEnseignantResponsable(EnseignantResponsableClasse enseignantResponsable, String resetPasswordLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(enseignantResponsable.getEmail());
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Pour réinitialiser votre mot de passe, veuillez suivre ce lien : " + resetPasswordLink);

        emailSender.send(message);
    }
}
