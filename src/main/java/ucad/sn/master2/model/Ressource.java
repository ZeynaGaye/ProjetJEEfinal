package ucad.sn.master2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    private String nom;
    private String description;
    private String type;
    private String chemin;
    private LocalDateTime dateAjout;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "ressource")
    private List<ConsultationRessource> consultations;

    public Ressource() {}

    public Ressource(String nom, String description, String type, String chemin, LocalDateTime dateAjout, Module module) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.chemin = chemin;
        this.dateAjout = dateAjout;
        this.module = module;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<ConsultationRessource> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationRessource> consultations) {
        this.consultations = consultations;
    }
}
