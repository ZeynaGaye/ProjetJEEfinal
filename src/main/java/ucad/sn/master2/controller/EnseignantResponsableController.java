package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.service.EnseignantResponsableService;

import java.util.List;

@Controller
public class EnseignantResponsableController {

    @Autowired
    private EnseignantResponsableService enseignantResponsableService;

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
