package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    @ManyToOne Enseignant enseignant;
    @ManyToOne Classe classe;
}
