package ucad.sn.master2.service;

import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.EnseignantResponsableClasse;
import ucad.sn.master2.model.Etudiant;

public interface EmailService {
    void sendPasswordResetEmail(Etudiant etudiant, String resetPasswordLink);

    void sendPasswordResetEmailEnseignant(Enseignant enseignant, String resetPasswordLink);

    void sendPasswordResetEmailEnseignantResponsable(EnseignantResponsableClasse savedEnseignantResponsable, String resetPasswordLink);
}
