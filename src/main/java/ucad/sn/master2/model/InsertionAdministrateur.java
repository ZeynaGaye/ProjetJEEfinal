package ucad.sn.master2.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ucad.sn.master2.model.Administrateur;
import ucad.sn.master2.service.AdministrateurService;
import ucad.sn.master2.service.RoleService;
import ucad.sn.master2.util.Genre;
import ucad.sn.master2.util.RoleType;

import java.util.Date;

@Component
public class InsertionAdministrateur {
    //private final AdministrateurService administrateurService;
    // private final RoleService roleService;

    // @Autowired
    // public InsertionAdministrateur(AdministrateurService administrateurService, RoleService roleService) {
        //  this.administrateurService = administrateurService;
        // this.roleService = roleService;
        // }

  //  public void insererAdministrateur() {
        // Création d'un administrateur
        //Administrateur administrateur = new Administrateur();
    //administrateur.setNom("Seck");
    //administrateur.setPrenom("Fatima");
    //administrateur.setDateNaissance(new Date()); // Exemple de date de naissance
    // administrateur.setGenre(Genre.FEMININ); // Exemple de genre
    // administrateur.setAdresse("Bargny");
    //administrateur.setEmail("fa@gmail.com");
    //administrateur.setMotDePasse("fafa");

        // Enregistrer l'administrateur
    //Administrateur nouveauAdministrateur = administrateurService.saveAdministrateur(administrateur);

        // Associer le rôle d'administrateur à l'utilisateur
    //roleService.addRoleToUser(nouveauAdministrateur, RoleType.ADMINISTRATEUR);

    //System.out.println("Nouvel administrateur ajouté : " + nouveauAdministrateur);
    //}
}
