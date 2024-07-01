package ucad.sn.master2.model;

import ucad.sn.master2.util.Genre;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Etudiant extends Users {
    private String numeroEtudiant;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @OneToMany(mappedBy = "etudiant")
    private List<ConsultationRessource> consultations;

    public Etudiant() {
        super();
    }

    public Etudiant(String nom, String prenom, Genre genre, String adresse, String email, String motDePasse, String numeroEtudiant, Classe classe) {
        super(nom, prenom, genre, adresse, email, motDePasse);
        this.numeroEtudiant = numeroEtudiant;
        this.classe = classe;
    }

    public Etudiant(String nom, String prenom, Genre genre, String adresse, String email, String motDePasse, String numeroEtudiant, String classe) {
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

    public List<ConsultationRessource> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationRessource> consultations) {
        this.consultations = consultations;
    }
}
