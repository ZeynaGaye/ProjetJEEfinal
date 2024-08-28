package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.service.ClasseService;
import ucad.sn.master2.service.EtudiantService;

import java.util.List;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;
    private final ClasseService classeService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService, ClasseService classeService) {
        this.etudiantService = etudiantService;
        this.classeService = classeService;
    }

    @GetMapping("/getAllEtudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @GetMapping("/add")
    public String showAddEtudiantForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "etudiants/add"; // Assurez-vous que ce chemin est correct
    }

    @PostMapping("/save")
    public String addEtudiant(@ModelAttribute Etudiant etudiant, Model model) {
        if (etudiant.getClasse() != null && etudiant.getClasse().getId() != null) {
            etudiantService.saveEtudiant(etudiant);
            return "redirect:/adminDashboard";
        } else {
            // Ajoutez un message d'erreur au modèle
            model.addAttribute("error", "Veuillez sélectionner une classe.");
            return "etudiants";
        }

    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "etudiants/edit";
    }

    @PostMapping("/update/{id}")
    public String updateEtudiant(@PathVariable Long id, @ModelAttribute("etudiant") Etudiant etudiant) {
        if (etudiant.getClasse() != null && etudiant.getClasse().getId() != null) {
            Classe classe = classeService.getClasseById(etudiant.getClasse().getId());
            etudiant.setClasse(classe);
        }
        etudiantService.updateEtudiant(id, etudiant);
        return "redirect:/etudiants";
    }

    @GetMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return "redirect:/etudiants";
    }

    @GetMapping("/liste")
    public String listEtudiants(Model model) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/liste"; // Assurez-vous que ce fichier existe : src/main/resources/templates/etudiants/liste.html
    }
}
