package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Module;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.EnseignantRepository;
import ucad.sn.master2.repository.ModuleRepository;

@Controller
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping("/modules/add")
    public String showAddModuleForm(Model model) {
        model.addAttribute("moduleForm", new Module());
        model.addAttribute("enseignants", enseignantRepository.findAll());
        model.addAttribute("classes", classeRepository.findAll());
        return "addModule";
    }

    @PostMapping("/modules/add")
    public String addModule(@ModelAttribute("moduleForm") Module moduleForm, Long enseignantId, Long classeId) {
        if (enseignantId == null) {
            throw new IllegalArgumentException("Enseignant ID must not be null");
        }
        if (classeId == null) {
            throw new IllegalArgumentException("Classe ID must not be null");
        }

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid enseignant Id:" + enseignantId));
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid classe Id:" + classeId));

        moduleForm.setEnseignant(enseignant);
        moduleForm.setClasse(classe);

        moduleRepository.save(moduleForm);
        return "redirect:/modules/add";
    }
}
