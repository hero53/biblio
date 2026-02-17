package com.dapsi.biblio.controller;


import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @PostMapping("/addLivre")
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        Livre savedLivre = livreRepository.save(livre);
        return ResponseEntity.ok(savedLivre);
    }

    @PostMapping("/addListLivre")
    public ResponseEntity<List<Livre>> addListLivre(@RequestBody List<Livre> livreList) {
        livreRepository.saveAll(livreList);
        return ResponseEntity.ok(livreList);
    }

    @GetMapping("/fetchLivre")
    public ResponseEntity<List<Livre>>fetchLivre() {
        List<Livre> lives = livreRepository.findAll();
        return ResponseEntity.ok(lives);
    }

    @PutMapping("updateLivre/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        Livre savedLivre = livreRepository.save(livre);
        return ResponseEntity.ok(savedLivre);
    }

    @DeleteMapping("/deleteLivre/{id}")
    public ResponseEntity<Livre> deleteLivre(@PathVariable Long id) {
        Livre isExiste = livreRepository.findById(id).orElse(null);
        if (isExiste != null) {
            livreRepository.delete(isExiste);
        }else  {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(isExiste);
    }


}
