package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.service.EnseignantService;

import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    @Autowired
    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping("getAllEnseignants")
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }

    @GetMapping("/{getEnseignantById}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        Enseignant enseignant = enseignantService.getEnseignantById(id);
        return new ResponseEntity<>(enseignant, HttpStatus.OK);
    }

    @PostMapping("addEnseignant")
    public ResponseEntity<Enseignant> addEnseignant(@RequestBody Enseignant enseignant) {
        Enseignant newEnseignant = enseignantService.saveEnseignant(enseignant);
        return new ResponseEntity<>(newEnseignant, HttpStatus.CREATED);
    }

    @PutMapping("/{updateEnseignan}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        Enseignant updatedEnseignant = enseignantService.updateEnseignant(id, enseignant);
        return new ResponseEntity<>(updatedEnseignant, HttpStatus.OK);
    }

    @DeleteMapping("/{deleteEnseignant}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
