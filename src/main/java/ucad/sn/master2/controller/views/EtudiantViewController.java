package ucad.sn.master2.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.Ressource;
import ucad.sn.master2.service.EtudiantService;
import ucad.sn.master2.service.ModuleService;
import ucad.sn.master2.service.RessourceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EtudiantViewController {
    private final EtudiantService etudiantService;
    private final ModuleService moduleService;
    private final RessourceService ressourceService;

    @Autowired
    public EtudiantViewController(EtudiantService etudiantService, ModuleService moduleService, RessourceService ressourceService) {
        this.etudiantService = etudiantService;
        this.moduleService = moduleService;
        this.ressourceService = ressourceService;
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


    //@GetMapping("/voir-cours")
   // public String voirCours(Model model) {
        // Add model attributes to list the courses
        //return "etudiants/voir-cours";
    //}

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



    @GetMapping("/etudiants/voir-cours")
    public String voirCours(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Etudiant etudiant = (Etudiant) etudiantService.findByEmail(email);

        if (etudiant.getClasse() == null) {
            model.addAttribute("errorMessage", "L'étudiant n'a pas de classe assignée.");
            return "etudiants/voir-cours"; // Afficher un message d'erreur sur la même page
        }

        Long classeId = etudiant.getClasse().getId();

        List<Module> modules = moduleService.getModulesForClasse(classeId);
        model.addAttribute("modules", modules);

        Map<Long, List<Ressource>> ressourcesParModule = new HashMap<>();
        for (Module module : modules) {
            List<Ressource> ressources = ressourceService.getRessourcesForModule(Long.valueOf(module.getName()));
            ressourcesParModule.put(Long.valueOf(module.getName()), ressources);
        }
        model.addAttribute("ressourcesParModule", ressourcesParModule);

        return "etudiants/voir-cours";
    }

}
