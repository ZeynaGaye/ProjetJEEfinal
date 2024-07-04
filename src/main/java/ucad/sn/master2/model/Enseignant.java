package ucad.sn.master2.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Enseignant extends Users{
    @ElementCollection
    private List<String> disciplines;
}
