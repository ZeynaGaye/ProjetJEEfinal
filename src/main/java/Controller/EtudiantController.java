package Controller;

import Repository.EtudiantRepository;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

}
