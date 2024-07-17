package ucad.sn.master2.service;

import ucad.sn.master2.model.Etudiant;

import java.util.List;

public interface EtudiantService {

    Etudiant saveEtudiant(Etudiant etudiant);

    Etudiant updateEtudiant(Long id, Etudiant etudiant);

    void deleteEtudiant(Long id);

    Etudiant getEtudiantById(Long id);

    List<Etudiant> getAllEtudiants();

//    long countStudents();
}
