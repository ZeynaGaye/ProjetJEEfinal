package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ucad.sn.master2.repository.EnseignantRepository;
import ucad.sn.master2.util.Genre;
import ucad.sn.master2.util.RoleType;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
public class Enseignant extends Users {

    private String matricule;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    @ManyToMany(mappedBy = "enseignants")
    private Set<Classe> Classes = new HashSet<>();

    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annonce> annonces = new ArrayList<>();


    public Enseignant() {}

    public Enseignant(String nom, String prenom, Date dateNaissance, Genre genre, String adresse, String email, String motDePasse, String matricule) {
        super(nom, prenom, dateNaissance, genre, adresse, email, motDePasse);
        this.matricule = matricule;

    }
    // Ajoutez un constructeur prenant en compte le rôle
    public Enseignant(String nom, String prenom, Date dateNaissance, Genre genre, String adresse, String email, String motDePasse, String matricule, RoleType roleType) {
        super(nom, prenom, dateNaissance, genre, adresse, email, motDePasse);
        this.matricule = matricule;
        this.addRole(new Role());
    }




    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void setClasse(Classe classe) {
        this.Classes.add(classe);
    }


}
