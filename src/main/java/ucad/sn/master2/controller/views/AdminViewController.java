package ucad.sn.master2.controller.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.service.*;
import ucad.sn.master2.util.RoleType;

import java.util.List;

@Controller
public class AdminViewController {

    private final EnseignantService enseignantService;
    private final EtudiantService etudiantService;
    private final ClasseService classeService;
    private final AdministrateurService administrateurService;
    private  final UserService userService;
    @Autowired
    public AdminViewController(EnseignantService enseignantService, EtudiantService etudiantService,
                               ClasseService classeService, AdministrateurService administrateurService, UserService userService) { // Modifiez ceci
        this.enseignantService = enseignantService;
        this.etudiantService = etudiantService;
        this.classeService = classeService;
        this.administrateurService = administrateurService;
        this.userService = userService;
    }

    @GetMapping("/admin/adminDashboard")
    public String adminDashboard(Model model) {
        // Implémentez votre logique pour le tableau de bord ici
        return "admin/adminDashboard";
    }

    @GetMapping("/admin/ajouter-enseignant")
    public String ajouterEnseignantForm(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "admin/ajouter-enseignant";
    }



    @GetMapping("/admin/ajouter-etudiant")
    public String ajouterEtudiantForm(Model model) {
       // model.addAttribute("etudiant", new Etudiant());
        //model.addAttribute("classes", classeService.trouverToutesLesClasses());
        List<Classe> classes = classeService.getAllClasses();
        model.addAttribute("etudiant", new Etudiant());
        model.addAttribute("classes", classes);
        return "admin/ajouter-etudiant";
    }


    @GetMapping("/classe/addClasse")
    public String AjouterClasseForm(Model model) {
        model.addAttribute("classe", new Classe());
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        return "classe/addClasse";
    }
    @GetMapping("/admin/listClasses")
    public String listeClasse(Model model) {
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "admin/listClasses";
    }
    @PostMapping("/admin/ajouter-enseignant")
    public String enregistrerEnseignant(@ModelAttribute Enseignant enseignant, @ModelAttribute RoleType roleType) {
        enseignantService.saveEnseignant(enseignant, roleType);
        return "redirect:/admin/ajouter-enseignant";
    }

    @PostMapping("/classe/addClasse")
    public String enregistrerClasse(@ModelAttribute Classe classe) {
        classeService.sauvegarderClasse(classe);
        return "redirect:/classe/addClasse";
    }

    @PostMapping("/admin/ajouter-etudiant")
    public String enregistrerEtudiant(@ModelAttribute Etudiant etudiant,
                                      @RequestParam Long classeId,
                                      RedirectAttributes redirectAttributes) {
        // Logique pour récupérer la classe par ID
        Classe classe = classeService.getClasseById(classeId);
        if (classe == null) {
            redirectAttributes.addFlashAttribute("error", "Classe non trouvée !");
            return "redirect:/etudiants/add";
        }

        // Logique pour récupérer la classe par ID
       // Classe classe = classeService.getClasseById(classeId);
        etudiant.setClasse(classe);

        // Ajouter un message de succès aux attributs de redirection
        redirectAttributes.addFlashAttribute("message", "Étudiant ajouté avec succès !");

        // Logique pour enregistrer l'étudiant
        etudiantService.saveEtudiant(etudiant);

        // Ajouter un message de succès aux attributs de redirection
        redirectAttributes.addFlashAttribute("message", "Étudiant ajouté avec succès !");
        return "redirect:/admin/ajouter-etudiant";
    }



    @GetMapping("/admin/liste")
    public String listeEtudiantsForm(Model model) {
        List<Etudiant> etudiants=etudiantService.getAllEtudiants();
        for(Etudiant etudiant:etudiants)
        {System.out.println("etudiants:"+etudiant.getNumeroEtudiant()+",Classe"+(etudiant.getClasse()!=null?etudiant.getClasse().getNom():"N/A"));}
        model.addAttribute("etudiants", etudiants);

        return "admin/liste";
    }

    @GetMapping("/admin/liste-enseignants")
    public String listeEnseignantsForm(Model model) {
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        return "admin/liste-enseignants";
    }

    @GetMapping("/admin/affecter-responsable")
    public String afficherFormulaireAffecterEnseignant(Model model) {
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "admin/affecter-responsable";
    }


    @PostMapping("/admin/affecter-responsable")
    public String affecterEnseignantClasse(@RequestParam("enseignantId") Long enseignantId,
                                           @RequestParam("classeId") Long classeId,
                                           RedirectAttributes redirectAttributes) {
        try {

            administrateurService.affecterEnseignantClasse(enseignantId, classeId);
            redirectAttributes.addFlashAttribute("message", "Affectation réussie !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'affectation : " + e.getMessage());
        }
        return "redirect:/admin/affecter-responsable";
    }

    @GetMapping("/admin/deconnexionadmin")
    public String deconnexionAdmin() {
        // Gérer la logique de déconnexion
        return "redirect:/logout";
    }


    @GetMapping("/admin/modifier-classe/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Classe classe = classeService.getClasseById(id);
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("classe", classe);
        model.addAttribute("enseignants", enseignants);
        return "admin/modifier-classe"; // Le nom du fichier HTML pour le formulaire
    }

    @PostMapping("/admin/modifier-classe/{id}")
    public String updateClasse(@PathVariable("id") Long id, @ModelAttribute Classe classe) {
        classeService.updateClasse(id, classe);
        return "redirect:/admin/listClasses"; // Redirige vers la liste des classes après modification
    }


}
