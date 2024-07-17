package ucad.sn.master2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Etudiant;
import ucad.sn.master2.repository.EtudiantRepository;
import ucad.sn.master2.service.EtudiantService;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Autowired
    public EtudiantServiceImpl(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        Optional<Etudiant> existingEtudiant = etudiantRepository.findById(id);
        if (existingEtudiant.isPresent()) {
            etudiant.setId(id); // Assure que l'ID est correctement défini pour l'objet mis à jour
            return etudiantRepository.save(etudiant);
        } else {
            throw new RuntimeException("Etudiant non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }



}
