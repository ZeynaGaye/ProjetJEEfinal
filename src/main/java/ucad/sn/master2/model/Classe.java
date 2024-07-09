package ucad.sn.master2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String niveau;

    @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = true)
    private Enseignant enseignantResponsable;

    @OneToMany(mappedBy = "classe")
    private List<Module> modules;

    @OneToMany(mappedBy = "classe")
    private List<Users> etudiants;

    public Classe() {}

    public Classe(String nom, String description, String niveau, Enseignant enseignantResponsable) {
        this.nom = nom;
        this.description = description;
        this.niveau = niveau;
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

    public List<Users> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Users> etudiants) {
        this.etudiants = etudiants;
    }

    public void setEnseignant(Enseignant enseignant) {
    }

    public List<Annonce> getAnnonces() {
        return null;
    }
}
