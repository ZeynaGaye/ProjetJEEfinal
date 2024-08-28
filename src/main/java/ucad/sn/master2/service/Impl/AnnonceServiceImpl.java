package ucad.sn.master2.service.Impl;

import org.springframework.stereotype.Service;
import ucad.sn.master2.model.Annonce;
import ucad.sn.master2.repository.AnnonceRepository;
import ucad.sn.master2.service.AnnonceService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;

    public AnnonceServiceImpl(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    @Override
    public List<Annonce> findAll() {
        return annonceRepository.findAll();
    }

    @Override
    public Annonce findById(Long id) {
        return annonceRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Annonce annonce) {
        annonceRepository.save(annonce);
    }

    @Override
    public void deleteById(Long id) {
        annonceRepository.deleteById(id);
    }

    public void createAnnonce(Annonce annonce) {
        annonce.setDateCreation(LocalDateTime.now());
        // Sauvegarder l'annonce dans la base de données
        annonceRepository.save(annonce);
    }

    @Override
    public void supprimerAnnonce(Long id) {
            annonceRepository.deleteById(id);
    }
}
