package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    private  final  ClasseService classeService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService, ClasseService classeService) {
        this.etudiantService = etudiantService;
        this.classeService = classeService;
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


    @GetMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return "redirect:/etudiants";
    }
}
