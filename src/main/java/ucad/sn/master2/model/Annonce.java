package ucad.sn.master2.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "annonces")
public class Annonce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    // Association avec l'enseignant
    private LocalDateTime dateCreation;

    // Autres champs...


    @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = false)
    private Enseignant enseignant;

    // Constructeurs

    public Annonce() {
        this.dateCreation = LocalDateTime.now();
    }
    public Annonce(String title, String description, LocalDateTime date_creation, Enseignant enseignant) {
        this.title = title;
        this.description = description;
        this.enseignant = enseignant;
        this.dateCreation = date_creation;
    }
//    private Date dateCreation;


    // getter


    // setter

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }


    public void setDescription(String description) {
        this.description = description;
    }
//    public Date getDateCreation() {
//        return dateCreation;
//    }
//
//    public void setDateCreation(Date dateCreation) {
//        this.dateCreation = dateCreation;
//    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
}
