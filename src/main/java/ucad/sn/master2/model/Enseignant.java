package ucad.sn.master2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ucad.sn.master2.util.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Enseignant extends Users {
    private String matricule;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    public Enseignant() {}

    public Enseignant(String nom, String prenom, Date dateNaissance, Genre genre, String adresse, String email, String motDePasse, String matricule) {
        super(nom, prenom, dateNaissance, genre, adresse, email, motDePasse);
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
}
