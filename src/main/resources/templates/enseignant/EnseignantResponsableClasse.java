package ucad.sn.master2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class EnseignantResponsableClasse extends Users{
    @OneToMany(mappedBy = "enseignantResponsable")
    private List<Classe> classes;
}
