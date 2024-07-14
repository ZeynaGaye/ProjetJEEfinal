package ucad.sn.master2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("adminName", "Admin");
        return "admin/adminDashboard";
    }
    @GetMapping("/etudiant/dashboard")
    public String etudiantDashboard() {
        return "etudiantDashboard"; // Fichier HTML dans src/main/resources/templates
    }

    @GetMapping("/enseignant/dashboard")
    public String enseignantDashboard() {
        return "enseignantDashboard"; // Fichier HTML dans src/main/resources/templates
    }

    @GetMapping("/enseignant-responsable/dashboard")
    public String enseignantResponsableDashboard() {
        return "enseignantResponsableDashboard"; // Fichier HTML dans src/main/resources/templates
    }

    @GetMapping("/default")
    public String defaultPage() {
        return "defaultPage"; // Fichier HTML dans src/main/resources/templates
    }
}
