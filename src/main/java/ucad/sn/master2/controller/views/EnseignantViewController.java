package ucad.sn.master2.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.*;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.service.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class EnseignantViewController {
    private final EnseignantService enseignantService;
    private final EtudiantService etudiantService;
    private final ClasseService classeService;
    private final AdministrateurService administrateurService;
    private final UserService userService;
    private final AnnonceService annonceService;

    @Autowired
    public EnseignantViewController(EnseignantService enseignantService, EtudiantService etudiantService, ClasseService classeService, AdministrateurService administrateurService, UserService userService, AnnonceService annonceService) {
        this.enseignantService = enseignantService;
        this.etudiantService = etudiantService;
        this.classeService = classeService;
        this.administrateurService = administrateurService;
        this.userService = userService;
        this.annonceService = annonceService;
    }
    @GetMapping("/enseignant/enseignantDashboard")
    public String enseignantDashboard(Model model) {
        // Récupérer l'email de l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Récupérer l'étudiant à partir de l'email
        Enseignant enseignant =  enseignantService.findByEmail(email);

        // Passer les informations de l'étudiant au modèle
        model.addAttribute("", enseignant.getNom());
        model.addAttribute("", enseignant.getPrenom());
        model.addAttribute("",enseignant.getMatricule());
        return "enseignant/enseignantDashboard";
    }


    @GetMapping("enseignant/annonces")
    public String annonces(Model model, Principal principal) {
        Enseignant enseignant = enseignantService.getEnseignantByEmail(principal.getName());
        if (enseignant != null) {
            List<Annonce> annonces = enseignant.getAnnonces();
            model.addAttribute("annonces", annonces);
            // Ajouter des logs pour vérifier
            System.out.println("Nombre d'annonces : " + annonces.size());
            for (Annonce annonce : annonces) {
                System.out.println("Annonce : " + annonce.getTitle());
            }
        } else {
            model.addAttribute("annonces", Collections.emptyList());
        }
        return "enseignant/annonces";
    }

    @GetMapping("/enseignant/ajouterAnnonce")
    public String afficherFormulaireAnnonce(Model model) {
        model.addAttribute("annonce", new Annonce());
        return "enseignant/ajouterAnnonce";
    }

    //    @GetMapping("/annonces/nouvelle")
//    public String nouvelleAnnonceForm(Model model) {
//        model.addAttribute("annonce", new Annonce());
//        return "enseignant/nouvelle-annonce";
//    }
//
    @PostMapping("/enseignant/ajouterAnnonce")
    public String creerAnnonce(@ModelAttribute Annonce annonce, Principal principal) {
        enseignantService.creerAnnonce(annonce, principal.getName());
        return "redirect:/enseignant/ajouterAnnonce";
    }
//    @PostMapping("/creerAnnonce")
//    public String creerAnnonce(@RequestBody Annonce annonce) {
//        try {
//            enseignantService.creerAnnonce(annonce);
//            return "redirect:/success";
//        } catch (Exception e) {
//            // Log the error and return an error page
//            return "error";
//        }
//    }

    @PostMapping("/annonces")
    public ResponseEntity<?> creerAnnonce(@RequestBody Annonce annonce) {
        try {
            enseignantService.creerAnnonce(annonce);
            return ResponseEntity.ok("Annonce créée avec succès.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/enseignant/modifierAnnonce/{id}")
    public String modifierAnnonce(@PathVariable("id") Long id, Model model) {
        Annonce annonce = annonceService.findById(id);
        model.addAttribute("annonce", annonce);
        return "enseignant/modifierAnnonce";
    }

    @GetMapping("/ressource/ressource-ajoutee")
    public String showAddRessourceForm(Model model) {
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "ressources/ressource-ajoutee";
    }
//
//    @GetMapping("/ressources")
//    public String listeRessources(Model model, Principal principal) {
//        Enseignant enseignant = enseignantService.getEnseignantByEmail(principal.getName());
//        model.addAttribute("ressources", enseignant.getRessources());
//        return "enseignant/ressources";
//    }
//
//    @GetMapping("/ressources/nouvelle")
//    public String nouvelleRessourceForm(Model model) {
//        model.addAttribute("ressource", new Ressource());
//        return "enseignant/nouvelle-ressource";
//    }
//
//    @PostMapping("/ressources/nouvelle")
//    public String ajouterRessource(@ModelAttribute Ressource ressource, Principal principal) {
//        enseignantService.ajouterRessource(ressource, principal.getName());
//        return "redirect:/enseignant/ressources";
//    }
//
//    @GetMapping("/modules")
//    public String listeCours(Model model, Principal principal) {
//        Enseignant enseignant = enseignantService.getEnseignantByEmail(principal.getName());
//        model.addAttribute("cours", enseignant.getModules());
//        return "enseignant/cours";
//    }
//
//    @GetMapping("/cours/nouveau")
//    public String nouveauCoursForm(Model model) {
//        model.addAttribute("cours", new Module());
//        return "enseignant/nouveau-cours";
//    }

//    @PostMapping("/cours/nouveau")
//    public String ajouterCours(@ModelAttribute  Module module, Principal principal) {
//        enseignantService.ajouterModule(module, principal.getName());
//        return "redirect:/enseignant/cours";
//    }
}
