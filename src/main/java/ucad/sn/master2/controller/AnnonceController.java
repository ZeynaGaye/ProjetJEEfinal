package ucad.sn.master2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.service.AnnonceService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/enseignant/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping("/annonces")
    public String getAllAnnonces(Model model) {
        model.addAttribute("annonces", annonceService.findAll());
        return "annonces";
    }

    @GetMapping("/ajouter")
    public String showAddAnnonceForm(Model model) {
        model.addAttribute("annonce", new Annonce());
        return "ajouterAnnonce";
    }

    @PostMapping("/ajouter")
    public String addAnnonce(@ModelAttribute Annonce annonce) {
        annonceService.save(annonce);
        annonce.setDateCreation(LocalDateTime.now());
        annonceService.createAnnonce(annonce);
        return "redirect:/enseignant/annonces";
    }
    @GetMapping("/modifier/{id}")
    public String showEditAnnonceForm(@PathVariable Long id, Model model) {
        Annonce annonce = annonceService.findById(id);
        model.addAttribute("annonce", annonce);
        return "modifierAnnonce";
    }

    @PostMapping("/modifier")
    public String updateAnnonce(@ModelAttribute Annonce annonce) {
        annonceService.save(annonce);
        return "redirect:/enseignant/annonces";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteById(id);
        return "redirect:/enseignant/annonces";
    }
}
