package ucad.sn.master2.service;

import ucad.sn.master2.model.Annonce;

import java.util.List;

public interface AnnonceService {
    List<Annonce> findAll();
    Annonce findById(Long id);
    void save(Annonce annonce);
    void deleteById(Long id);

    void createAnnonce(Annonce annonce);

    void supprimerAnnonce(Long id);
}

