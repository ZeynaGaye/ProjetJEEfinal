package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ucad.sn.master2.util.Genre;

import java.util.Date;

@Entity
public class Etudiant extends Users {

    @Column(name = "numero_etudiant", unique = true) // Ajout d'une colonne unique pour le numéro d'étudiant
    private String numeroEtudiant;

    @ManyToOne
    @JoinColumn(name = "classe_id") // Référence vers la classe à laquelle l'étudiant est associé
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
