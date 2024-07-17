package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ucad.sn.master2.util.Genre;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Enseignant extends Users {

    private String matricule;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    @ManyToMany(mappedBy = "enseignants")
    private Set<Classe> classes = new HashSet<>();

    public Enseignant() {
    }

    public Enseignant(String nom, String prenom, Date dateNaissance, Genre genre, String adresse, String email, String motDePasse, String matricule) {
        super(nom, prenom, dateNaissance, genre, adresse, email, motDePasse);
        this.matricule = matricule;
    }


}
