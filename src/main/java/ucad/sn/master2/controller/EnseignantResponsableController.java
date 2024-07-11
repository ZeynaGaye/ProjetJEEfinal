package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.service.ClasseService;
import ucad.sn.master2.service.EnseignantResponsableService;
import ucad.sn.master2.service.EnseignantService;

import java.util.List;

@Controller
public class EnseignantResponsableController {

    @Autowired
    private EnseignantResponsableService enseignantResponsableService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private ClasseService classeService;

    @GetMapping("/ajouter-enseignant")
    public String afficherFormulaireAjouterEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "enseignant-responsable/ajouter-enseignant";
    }

    @PostMapping("/ajouter-enseignant")
    public String ajouterEnseignant(@ModelAttribute Enseignant enseignant) {
        enseignantService.saveEnseignant(enseignant);
        return "redirect:/enseignant-responsable/liste-enseignants";
    }

    @GetMapping("/liste-enseignants")
    public String listeEnseignants(Model model) {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("enseignants", enseignants);
        return "enseignant-responsable/liste-enseignants";
    }

    @GetMapping("/affecter-enseignant")
    public String afficherFormulaireAffecterEnseignant(Model model) {
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "enseignant-responsable/affecter-enseignant";
    }

//    @PostMapping("/affecter-enseignant")
//   public String affecterEnseignantClasse(@RequestParam Long enseignantId, @RequestParam Long classeId) {
//        EnseignantService.affecterEnseignantClasse(enseignantId, classeId);
//        return "redirect:/enseignant-responsable/liste-classes";
//    }
@PostMapping("/affecter-enseignant-classe")
public String affecterEnseignantClasse(@RequestParam("enseignantId") Long enseignantId,
                                       @RequestParam("classeId") Long classeId) {
    EnseignantService.affecterEnseignantClasse(enseignantId, classeId);
    // Redirigez vers une page de confirmation ou de détails de la classe, par exemple
    return "redirect:/enseignant-responsable/classe-details?classeId=" + classeId;
}
    @GetMapping("/liste-classes")
    public String listeClasses(Model model) {
        List<Classe> classes = classeService.trouverToutesLesClasses();
        model.addAttribute("classes", classes);
        return "enseignant-responsable/liste-classes";
    }
    @GetMapping("/enseignant-responsable/ajouter-enseignant")
    public String afficherFormulaireAjoutEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant()); // Assurez-vous d'initialiser correctement votre objet Enseignant
        return "ajout-enseignant";
    }

    @GetMapping("/classes")
    public String getAllClasses(Model model) {
        List<Classe> classes = enseignantResponsableService.getAllClasses();
        model.addAttribute("classes", classes);
        return "classes";
    }

    @GetMapping("/add-annonce")
    public String showAddAnnonceForm(Model model) {
        model.addAttribute("annonce", new Annonce());
        return "add-annonce";
    }

    @PostMapping("/add-annonce")
    public String addAnnonce(@ModelAttribute("annonce") Annonce annonce) {
        enseignantResponsableService.addAnnonce(annonce);
        return "redirect:/classes";
    }

    @GetMapping("/add-module")
    public String showAddModuleForm(Model model) {
        model.addAttribute("module", new Module());
        return "add-module";
    }

    @PostMapping("/add-module")
    public String addModule(@ModelAttribute("module") Module module) {
        enseignantResponsableService.addModule(module);
        return "redirect:/classes";
    }
}
