package ucad.sn.master2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ucad.sn.master2.model.Enseignant;
import ucad.sn.master2.model.Ressource;
import ucad.sn.master2.model.Users;
import ucad.sn.master2.service.ModuleService;
import ucad.sn.master2.service.RessourceService;
import org.springframework.security.core.Authentication;
import ucad.sn.master2.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/ressources")
public class RessourceController {

    @Autowired
    private RessourceService ressourceService;
    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

    // Page de formulaire pour ajouter une ressource
    @GetMapping("/add")
    public String showAddRessourceForm(Model model) {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            UserDetails userDetails = (UserDetails) principal;
            // Récupérer l'email ou autre identifiant à partir de userDetails
            Users user = userService.loadUserByUsername(userDetails.getUsername());

            if (user instanceof Enseignant) {
                Enseignant enseignant = (Enseignant) user;
                model.addAttribute("classes", enseignant.getClasses());
            } else {
                return "error"; // Gérer le cas où l'utilisateur n'est pas de type Enseignant
            }
        } else {
            return "error"; // Gérer le cas où le principal n'est pas de type User
        }

        model.addAttribute("ressource", new Ressource());
        model.addAttribute("modules", moduleService.getAllModules());
        return "ressource/add-ressource-form"; // Vue HTML pour le formulaire d'ajout de ressource
    }

    // Traitement du formulaire pour ajouter une ressource
    @PostMapping("/add")
    public String addRessource(@ModelAttribute("ressource") Ressource ressource,
                               @RequestParam("file") MultipartFile file,
                               Model model) {
        try {
            // Vérifier si un fichier a été téléchargé
            if (!file.isEmpty()) {
                // Définir le chemin où le fichier sera enregistré
                String uploadsDir = "src/main/resources/static/uploader/";
                String filePath = uploadsDir + file.getOriginalFilename();
                Path path = Paths.get(filePath);

                // Enregistrer le fichier sur le disque
                Files.write(path, file.getBytes());

                // Mettre à jour les informations de la ressource avec le fichier téléchargé
                ressource.setChemin("/uploader/" + file.getOriginalFilename());
                ressource.setDateAjout(LocalDateTime.now());
            }

            // Enregistrer la ressource dans la base de données
            Ressource savedRessource = ressourceService.addRessource(ressource);
            model.addAttribute("ressource", savedRessource);
            return "ressource/ressource-ajoutee"; // Vue HTML pour confirmer l'ajout de la ressource
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Erreur lors du téléchargement du fichier.");
            return "add-ressource-form";
        }
    }

    // Méthode pour afficher toutes les ressources
    //@GetMapping("/list")
    //public String listAllRessources(Model model) {
        //List<Ressource> ressources = ressourceService.getAllRessources();
        //model.addAttribute("ressources", ressources);
        //eturn "list-ressources"; // Vue HTML pour afficher toutes les ressources
    //}

    // Méthode pour afficher les ressources de l'enseignant connecté
    @GetMapping("/list")
    public String listRessourcesByEnseignant(Model model) {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Users user = userService.loadUserByUsername(userDetails.getUsername());

            if (user instanceof Enseignant) {
                Enseignant enseignant = (Enseignant) user;
                List<Ressource> ressources = ressourceService.getRessourcesByEnseignant(enseignant);
                model.addAttribute("ressources", ressources);
                return "ressource/list-ressources"; // Vue HTML pour afficher les ressources de l'enseignant
            } else {
                return "error"; // Gérer le cas où l'utilisateur n'est pas de type Enseignant
            }
        } else {
            return "error"; // Gérer le cas où le principal n'est pas de type User
        }
    }
}
