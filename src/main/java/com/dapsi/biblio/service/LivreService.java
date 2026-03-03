package com.dapsi.biblio.service;

import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public Livre createLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public List<Livre> addListLivre(List<Livre> livreList) {
        return livreRepository.saveAll(livreList);
    }

    public List<Livre> fetchLivre() {
        return livreRepository.findAll();
    }

    public Livre updateLivre(Long id, Livre livre) {
        livre.setId(id);
        return livreRepository.save(livre);
    }

    public Optional<Livre> deleteLivre(Long id) {
        Optional<Livre> livre = livreRepository.findById(id);
        livre.ifPresent(livreRepository::delete);
        return livre;
    }

    public Optional<Livre> findById(Long id) {
        return livreRepository.findById(id);
    }
}
