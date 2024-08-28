package ucad.sn.master2.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.service.ClasseService;
import ucad.sn.master2.service.EnseignantService;
import ucad.sn.master2.service.EtudiantService;

import java.util.List;

@Controller
public class EnseignantResponsableView {
    private final EtudiantService etudiantService;
    private final ClasseService classeService;
    private final EnseignantService enseignantService;

    public EnseignantResponsableView(EtudiantService etudiantService, ClasseService classeService, EnseignantService enseignantService) {
        this.etudiantService = etudiantService;
        this.classeService = classeService;
        this.enseignantService = enseignantService;
    }

    @GetMapping("/enseignant_responsable/enseignantresponsableDashboard")
    public String enseignantresponsableDashboard() {
        return "enseignant_responsable/enseignantresponsableDashboard"; // Fichier HTML dans src/main/resources/templates
    }



    @GetMapping("/enseignantresponsable/addModule")
    public String redirectToAddModuleForm() {
        return "redirect:/modules/add"; // Redirection vers le formulaire d'ajout de ressource
    }




    @GetMapping("/enseignantresponsable/listClasses")
    public String listeClasse1(Model model) {
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "admin/listClasses";
    }

    @PostMapping("/enseignantresponsable/addClasse")
    public String enregistrerClasse1(@ModelAttribute Classe classe) {
        classeService.sauvegarderClasse(classe);
        return "redirect:/classe/addClasse";
    }


    @GetMapping("/enseignantresponsable/addClasse")
    public String AjouterClasseForm1(Model model) {
        model.addAttribute("classe", new Classe());
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        return "classe/addClasse";
    }


    @GetMapping("/enseignantresponsable/liste")
    public String listeEtudiantsForm1(Model model) {
        List<Etudiant> etudiants=etudiantService.getAllEtudiants();
        for(Etudiant etudiant:etudiants)
        {System.out.println("etudiants:"+etudiant.getNumeroEtudiant()+",Classe"+(etudiant.getClasse()!=null?etudiant.getClasse().getNom():"N/A"));}
        model.addAttribute("etudiants", etudiants);

        return "admin/liste";
    }

    @GetMapping("/enseignantresponsable/liste-enseignant")
    public String listeEnseignantsForm1(Model model) {
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        return "admin/liste-enseignants";
    }


    @GetMapping("/enseignantresponsable/liste-classes")
    public String listeClasse2(Model model) {
        model.addAttribute("classes", classeService.trouverToutesLesClasses());
        return "enseignantresponsable/liste-classes";
    }

}