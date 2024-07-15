package ucad.sn.master2.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "classe_enseignant",
            joinColumns = @JoinColumn(name = "classe_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
    private Set<Enseignant> enseignants = new HashSet<>();
    @OneToMany(mappedBy = "id")
    private List<Module> modules;

    @OneToMany(mappedBy = "id")
    private List<Users> etudiants,enseignant;


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


    public List<Annonce> getAnnonces(List<Annonce> annonce) {
        return annonce;
    }


    public List<Users> getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(List<Users> enseignant) {
        this.enseignant = enseignant;
    }
}
