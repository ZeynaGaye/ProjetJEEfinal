package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String niveau;

    @ManyToOne
    private Enseignant responsable;

    @OneToMany(mappedBy = "classe")
    private List<Etudiant> etudiants;
    private List<Module> modules;

    @OneToMany(mappedBy = "classe")
    private List<Annonce> annonces;
}
