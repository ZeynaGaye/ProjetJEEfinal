package ucad.sn.master2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConsultationRessource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    private LocalDateTime dateConsultation;
    private int nombreConsultations;

    public ConsultationRessource() {}

    public ConsultationRessource(Ressource ressource, Etudiant etudiant, LocalDateTime dateConsultation, int nombreConsultations) {
        this.ressource = ressource;
        this.etudiant = etudiant;
        this.dateConsultation = dateConsultation;
        this.nombreConsultations = nombreConsultations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public int getNombreConsultations() {
        return nombreConsultations;
    }

    public void setNombreConsultations(int nombreConsultations) {
        this.nombreConsultations = nombreConsultations;
    }
}
