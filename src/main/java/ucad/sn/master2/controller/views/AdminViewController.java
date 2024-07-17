package ucad.sn.master2.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminViewController {

    @GetMapping("/admin/adminDashboard")
    public String adminDashboard() {
        return "admin/adminDashboard";
    }

    @GetMapping("/ajouter-enseignant")
    public String ajouterEnseignant() {
        return "ajouter-enseignant";
    }

    @GetMapping("/etudiants/liste")
    public String listeEtudiants() {
        return "etudiants/liste";
    }

    @GetMapping("/liste-enseignants")
    public String listeEnseignants() {
        return "liste-enseignants";
    }


    @GetMapping("/deconnexionadmin")
    public String deconnexionadmin() {
        // Handle logout logic
        return "redirect:/logout";
    }



}