package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ucad.sn.master2.model.Administrateur;
import ucad.sn.master2.model.Classe;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.repository.ClasseRepository;
import ucad.sn.master2.repository.UserRepository;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("userForm", new Users());
        model.addAttribute("classes", classeRepository.findAll());
        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(Users userForm, String userType, String numeroEtudiant, Long classeId, String matricule) {
        Users user;

        switch (userType) {
            case "Etudiant":
                Classe classe = classeRepository.findById(classeId).orElseThrow(() -> new IllegalArgumentException("Classe not found: " + classeId));
                user = new Etudiant(userForm.getNom(), userForm.getPrenom(), userForm.getGenre(), userForm.getAdresse(), userForm.getEmail(), userForm.getMotDePasse(), numeroEtudiant, classe);
                break;
            case "Enseignant":
                user = new Enseignant(userForm.getNom(), userForm.getPrenom(), userForm.getGenre(), userForm.getAdresse(), userForm.getEmail(), userForm.getMotDePasse(), matricule);
                break;
            case "Administrateur":
                user = new Administrateur(userForm.getNom(), userForm.getPrenom(), userForm.getGenre(), userForm.getAdresse(), userForm.getEmail(), userForm.getMotDePasse());
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }

        usersRepository.save(user);
        return "redirect:/users/add";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Users> usersList = usersRepository.findAll();
        model.addAttribute("users", usersList);
        return "listUsers";
    }
}
