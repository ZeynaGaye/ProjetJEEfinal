package ucad.sn.master2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titre;
    private String contenu;
    private Date datePublication;

    @ManyToOne
    private Classe classe;
}
