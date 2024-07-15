package ucad.sn.master2.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {

    @GetMapping("/admin/adminDashboard")
    public String adminDashboard() {
        return "admin/adminDashboard";
    }
    @GetMapping("/ajouter-etudiants")
    public String ajouterEtudiant() {
        return "ajouter-etudiant";
    }
    @GetMapping("/ajouter-enseignant")
    public String ajouterEnseignant() {
        return "ajouter-enseignant";
    }

    @GetMapping("/liste-etudiants")
    public String listeEtudiants() {
        return "liste-etudiants";
    }

    @GetMapping("/liste-enseignants")
    public String listeEnseignants() {
        return "liste-enseignants";
    }
}