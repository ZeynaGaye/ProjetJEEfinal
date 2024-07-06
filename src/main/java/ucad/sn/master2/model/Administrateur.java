package ucad.sn.master2.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import ucad.sn.master2.util.Genre;

import java.util.Date;

@Entity
@Data
public class Administrateur extends Users {

    public Administrateur() {}

    public Administrateur(String nom, String prenom, Date dateNaissance, Genre genre, String adresse, String email, String motDePasse) {
        super(nom, prenom, dateNaissance, genre, adresse, email, motDePasse);
    }
}
