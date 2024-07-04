package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ucad.sn.master2.util.Genre;

import java.util.Date;

@Entity

public class Etudiant extends Users {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id ;
    private String numeroEtudiant;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    public Etudiant() {}

    public Etudiant(String nom, String prenom, Date dateNaissance, Genre genre, String adresse, String email, String motDePasse, String numeroEtudiant, Classe classe) {
        super(nom, prenom, dateNaissance, genre, adresse, email, motDePasse);
        this.numeroEtudiant = numeroEtudiant;
        this.classe = classe;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
