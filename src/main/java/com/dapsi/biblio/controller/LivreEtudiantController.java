package com.dapsi.biblio.controller;

import com.dapsi.biblio.dto.EmpruntRequest;
import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.model.LivreEtudiant;
import com.dapsi.biblio.repository.EtudiantRepository;
import com.dapsi.biblio.repository.LivreEtudiantRepository;
import com.dapsi.biblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreEtudiantController {
    @Autowired
    private LivreEtudiantRepository livreEtudiantRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @PostMapping("/doEmprun")
    public ResponseEntity<LivreEtudiant> doEmprun(@RequestBody EmpruntRequest request) {
        Livre livre = livreRepository.findById(request.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        Etudiant etudiant = etudiantRepository.findById(request.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));


        LivreEtudiant emprunt = new LivreEtudiant();
        emprunt.setLivre(livre);
        emprunt.setEtudiant(etudiant);
        return ResponseEntity.ok(livreEtudiantRepository.save(emprunt));
    }

    @GetMapping("/emprunts")
    public List<LivreEtudiant> getAll() {
        return livreEtudiantRepository.findAll();
    }

}
