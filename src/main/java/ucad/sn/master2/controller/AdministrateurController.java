package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Administrateur;
import ucad.sn.master2.service.AdministrateurService;

import java.util.List;

@RestController
@RequestMapping("/admin/administrateurs")
public class AdministrateurController {

    private final AdministrateurService administrateurService;

    @Autowired
    public AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    @GetMapping
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        List<Administrateur> administrateurs = administrateurService.getAllAdministrateurs();
        return new ResponseEntity<>(administrateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        Administrateur administrateur = administrateurService.getAdministrateurById(id);
        return new ResponseEntity<>(administrateur, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Administrateur> addAdministrateur(@RequestBody Administrateur administrateur) {
        Administrateur newAdministrateur = administrateurService.saveAdministrateur(administrateur);
        return new ResponseEntity<>(newAdministrateur, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Long id, @RequestBody Administrateur administrateur) {
        Administrateur updatedAdministrateur = administrateurService.updateAdministrateur(id, administrateur);
        return new ResponseEntity<>(updatedAdministrateur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
