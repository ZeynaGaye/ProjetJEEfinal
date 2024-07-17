package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.Ressource;
import ucad.sn.master2.service.ClasseService;
import ucad.sn.master2.service.EtudiantService;
import ucad.sn.master2.service.RessourceService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;
    @Autowired
    private RessourceService ressourceService;

    private  final  ClasseService classeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EtudiantController(EtudiantService etudiantService, ClasseService classeService, PasswordEncoder passwordEncoder) {
        this.etudiantService = etudiantService;
        this.classeService = classeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getAllEtudiants(Model model) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/liste";
    }

    @GetMapping("/add")
    public String showAddEtudiantForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "etudiants/add";
    }

    @PostMapping("/save")
    public String addEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantService.saveEtudiant(etudiant);
        return "redirect:/etudiants";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "etudiants/edit";
    }


    @PostMapping("/update/{id}")
    public String updateEtudiant(@PathVariable Long id, @ModelAttribute Etudiant etudiant) {
        // Récupérer l'objet Classe à partir de l'ID fourni dans l'étudiant
        Long classeId = etudiant.getClasse().getId(); // Supposons que getId() retourne l'ID de la classe

        Classe classe = classeService.getClasseById(classeId); // Méthode hypothétique pour récupérer la classe par ID

        // Vérifier si la classe est trouvée et l'assigner à l'étudiant
        if (classe != null) {
            etudiant.setClasse(classe);
        } else {


            return "redirect:/error-page";
        }

        // Mettre à jour l'étudiant avec la nouvelle classe
        etudiantService.updateEtudiant(id, etudiant);

        return "redirect:/etudiants";
    }


    // Méthode pour afficher les ressources de la classe de l'étudiant
    @GetMapping("/classe")
    public String getRessourcesByClasse(Principal principal, Model model) {
        // Récupérer l'utilisateur connecté (étudiant)
        String email = principal.getName();
        Etudiant etudiant = (Etudiant) etudiantService.findByEmail(email);

        // Récupérer les ressources de la classe de l'étudiant
        List<Ressource> ressources = ressourceService.getRessourcesByClasseId(etudiant.getClasse().getId());

        model.addAttribute("ressources", ressources);
        return "ressource/list-ressources"; // Vue HTML pour afficher les ressources
    }

    @GetMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return "redirect:/etudiants";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Principal principal, Model model) {
        String email = principal.getName();
        Etudiant etudiant = (Etudiant) etudiantService.findByEmail(email);

        // Accéder aux méthodes getPassword et setPassword à travers l'instance d'Etudiant
        if (!passwordEncoder.matches(currentPassword, etudiant.getPassword())) {
            model.addAttribute("error", "Mot de passe actuel incorrect");
            return "etudiants/modifier-mot-de-passe";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Les nouveaux mots de passe ne correspondent pas");
            return "etudiants/modifier-mot-de-passe";
        }

        etudiant.setPassword(passwordEncoder.encode(newPassword));
        etudiantService.updateEtudiant(etudiant.getId(), etudiant);

        return "redirect:/etudiants/etudiantDashboard";
    }
}
