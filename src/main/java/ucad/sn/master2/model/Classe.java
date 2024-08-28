package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String niveau;
    // @Pattern(regexp = "\\d{4}/\\d{4}", message = "Veuillez entrer l'année au format YYYY/YYYY")
    private String annee;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignantResponsable;



    @ManyToMany
    @JoinTable(
            name = "classe_enseignant",
            joinColumns = @JoinColumn(name = "classe_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
    private Set<Enseignant> enseignants = new HashSet<>();

    @OneToMany(mappedBy = "id")
    private List<Module> modules;

    @OneToMany(mappedBy = "classe")
    private List<Etudiant> etudiants;

    @OneToMany(mappedBy = "classe")
    private List<Enseignant> enseignant;



    public Classe() {}

    public Classe(String nom, String description, String niveau, String annee, Enseignant enseignantResponsable) {
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
        this.annee = annee;
        this.enseignantResponsable = enseignantResponsable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }



    public Enseignant getEnseignantResponsable() {
        return enseignantResponsable;
    }

    public void setEnseignantResponsable(Enseignant enseignantResponsable) {
        this.enseignantResponsable = enseignantResponsable;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public List<Annonce> getAnnonces(List<Annonce> annonce) {
        return annonce;
    }

    public String getAnnee() {
        return annee;
    }

    public Set<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public void setEnseignant(List<Enseignant> enseignant) {
        this.enseignant = enseignant;
    }

    public List<Enseignant> getEnseignant() {
        return enseignant;
    }
}
