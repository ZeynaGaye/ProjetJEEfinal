package models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;


@Entity
public class Etudiant extends Utilisateur {

    @OneToOne
    private Classe classe;

    // Constructeurs
    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String email, String motDePasse, Classe classe) {
        super(nom, prenom, email, motDePasse, "etudiant");
        this.classe = classe;
    }

    // Getters et Setters
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}