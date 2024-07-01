package ucad.sn.master2.model;

import jakarta.persistence.Entity;
import ucad.sn.master2.util.Genre;

@Entity
public class Administrateur extends Users {

    public Administrateur() {
        super();
    }

    public Administrateur(String nom, String prenom, Genre genre, String adresse, String email, String motDePasse) {
        super(nom, prenom, genre, adresse, email, motDePasse);
        // Aucun autre attribut spécifique à l'administrateur pour le moment
    }
}
