package ucad.sn.master2.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.service.EnseignantService;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final EnseignantService enseignantService;

    @Autowired
    public AdminViewController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {
        // Implémentez votre logique pour le tableau de bord ici
        return "admin/adminDashboard";
    }

    @GetMapping("/ajouter-enseignant")
    public String ajouterEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "admin/ajouter-enseignant";
    }

    @PostMapping("/ajouter-enseignant")
    public String enregistrerEnseignant(@ModelAttribute Enseignant enseignant) {
        // Appelez la méthode POST du contrôleur REST pour ajouter l'enseignant
        enseignantService.saveEnseignant(enseignant);
        return "redirect:/admin/liste-enseignants";
    }

    @GetMapping("/liste-etudiants")
    public String listeEtudiants() {
        return "admin/liste-etudiants";
    }

    @GetMapping("/liste-enseignants")
    public String listeEnseignants() {
        return "admin/liste-enseignants";
    }
}
