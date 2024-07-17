package ucad.sn.master2.service;

import ucad.sn.master2.model.Etudiant;

public interface EmailService {
    void sendPasswordResetEmail(Etudiant etudiant, String resetPasswordLink);

}
