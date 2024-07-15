package ucad.sn.master2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ucad.sn.master2.model.InsertionAdministrateur;

@SpringBootApplication
public class ProjetJee2024FinalApplication {



   // private final InsertionAdministrateur insertionAdministrateur;

//    @Autowired
//    public ProjetJee2024FinalApplication(InsertionAdministrateur insertionAdministrateur) {
//        this.insertionAdministrateur = insertionAdministrateur;
//    }



    public static void main(String[] args) {
        SpringApplication.run(ProjetJee2024FinalApplication.class, args);
    }



    // @Bean
    // public CommandLineRunner commandLineRunner() {
    // return args -> {
     //insertionAdministrateur.insererAdministrateur();
    // };
    //}
}
