package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EnseignantRepository;

@Controller
public class ClasseController {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @GetMapping("/classes/add")
    public String showAddClassForm(Model model) {
        model.addAttribute("classeForm", new Classe());
        model.addAttribute("enseignants", enseignantRepository.findAll());
        return "addClasse";
    }

    @PostMapping("/classes/add")
    public String addClass(Classe classeForm, Long enseignantId) {
        if (enseignantId == null) {
            throw new IllegalArgumentException("Enseignant ID must not be null");
        }
        Enseignant enseignantResponsable = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid enseignant Id:" + enseignantId));
        classeForm.setEnseignantResponsable(enseignantResponsable);
        classeRepository.save(classeForm);
        return "redirect:/classes/add";
    }
}
