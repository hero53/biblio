package com.dapsi.biblio.controller;

import com.dapsi.biblio.model.Emprunt;
import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.repository.EtudiantRepository;
import com.dapsi.biblio.repository.EmpruntRepository;
import com.dapsi.biblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunter")
public class EmpruntController {
    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

//    @PostMapping("/doEmprun")
//    public ResponseEntity<Emprunt> doEmprun(@RequestBody EmpruntRequest request) {
//        Livre livre = livreRepository.findById(request.getLivreId())
//                .orElseThrow(() -> new RuntimeException("Livre introuvable"));
//
//        Etudiant etudiant = etudiantRepository.findById(request.getEtudiantId())
//                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
//
//        Emprunt emprunt = new Emprunt();
//        emprunt.setLivre(livre);
//        emprunt.setEtudiant(etudiant);
//        return ResponseEntity.ok(empruntRepository.save(emprunt));
//    }

    @GetMapping("/emprunts")
    public List<Emprunt> getAll() {
        return empruntRepository.findAll();
    }

}
