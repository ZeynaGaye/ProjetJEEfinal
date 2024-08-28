package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.EnseignantResponsableClasse;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EnseignantRepository;
import ucad.sn.master2.repository.EnseignantResponsableClasseRepository;

@Controller
public class ClasseController {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private EnseignantResponsableClasseRepository enseignantResponsableClasseRepository;

    @GetMapping("/classes/add")
    public String showAddClassForm(Model model) {
        model.addAttribute("classeForm", new Classe());
        model.addAttribute("enseignants", enseignantRepository.findAll());
        return "classe/addClasse";
    }

    @PostMapping("/classes/add")
    public String addClass(@ModelAttribute("classeForm") Classe classeForm,
                           @RequestParam("enseignantId") Long enseignantId) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid enseignant ID: " + enseignantId));


       // classeForm.setEnseignantResponsable(enseignant);
       classeForm.getEnseignants().add(enseignant);
        classeRepository.save(classeForm);

        return "redirect:/classes/add";
    }
}
