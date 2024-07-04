package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.*;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.service.EnseignantResponsableService;

import java.util.List;

@RestController
@RequestMapping("/api/responsable")
public class EnseignantResponsableController {
    @Autowired
    private EnseignantResponsableService responsableService;

    @PostMapping("/gererModules/{classeId}")
    public ResponseEntity<Void> gererModules(@PathVariable Long classeId, @RequestBody List<Module> modules){
        responsableService.gererModules(classeId, modules);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/affecterEnseignant/{classeId}/{enseignantId}")
    public ResponseEntity<Void> affecterEnseignant(@PathVariable Long classeId, @RequestBody Enseignant enseignant){
        responsableService.affecterEnseignant(classeId,enseignant);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/gererEtudiants/{classeId}")
    public ResponseEntity<Void> gererEtudiants(@PathVariable Long classeId, @RequestBody List<Users> etudiants){
        responsableService.gererEtudiants(classeId,etudiants);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/gererAnnonces/{classeId}")
    public ResponseEntity<Void> gererAnnonces(@PathVariable Long classeId, @RequestBody List<Annonce> annonces){
        responsableService.gererAnnonces(classeId,annonces);
        return ResponseEntity.ok().build();
    }

}
