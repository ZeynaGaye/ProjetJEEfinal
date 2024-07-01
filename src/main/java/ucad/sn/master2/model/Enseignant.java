package ucad.sn.master2.model;

<<<<<<< HEAD
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Enseignant extends Users{
    @ElementCollection
    private List<String> disciplines;
=======
import ucad.sn.master2.util.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Enseignant extends Users {
    private String matricule;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    public Enseignant() {
        super();
    }

    public Enseignant(String nom, String prenom, Genre genre, String adresse, String email, String motDePasse, String matricule) {
        super(nom, prenom, genre, adresse, email, motDePasse);
        this.matricule = matricule;
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
>>>>>>> b0086cf (Ajout de nouveaux modèles)
}
