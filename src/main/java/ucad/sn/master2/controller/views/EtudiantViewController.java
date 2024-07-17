package ucad.sn.master2.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.service.EtudiantService;
;

@Controller
public class EtudiantViewController {
    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantViewController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // @GetMapping("etudiants/etudiantDashboard")
    // public String showAjouterEtudiantPage(Model model) {
    //Ajoutez les attributs nécessaires au modèle ici, si nécessaire
    // return "etudiants/etudiantDashboard";
    ///}

    @GetMapping("/etudiants/etudiantDashboard")
    public String showEtudiantDashboard(Model model) {
        // Récupérer l'email de l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Récupérer l'étudiant à partir de l'email
        Etudiant etudiant = (Etudiant) etudiantService.findByEmail(email);

        // Passer les informations de l'étudiant au modèle
        model.addAttribute("nomEtudiant", etudiant.getNom());
        model.addAttribute("numeroCarte", etudiant.getNumeroEtudiant());

        return "etudiants/etudiantDashboard";
    }


    @GetMapping("/voir-cours")
    public String voirCours(Model model) {
        // Add model attributes to list the courses
        return "etudiants/voir-cours";
    }

    @GetMapping("etudiants/modifier-mot-de-passe")
    public String modifierMotDePasse(Model model) {
        // Add model attributes to handle password change
        return "etudiants/modifier-mot-de-passe";
    }

    @GetMapping("classe/add")
    public String Ajoutclasse(Model model) {
        // Add model attributes to handle password change
        return "classe/add";
    }



    @GetMapping("/deconnexion")
    public String deconnexion() {
        // Handle logout logic
        return "redirect:/logout";
    }



}
