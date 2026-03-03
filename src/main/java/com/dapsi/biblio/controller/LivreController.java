package com.dapsi.biblio.controller;


import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.service.LivreService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@RestController
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PostMapping("/addLivre")
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        Livre savedLivre = livreService.createLivre(livre);
        return ResponseEntity.ok(savedLivre);
    }

    @PostMapping("/addListLivre")
    public ResponseEntity<List<Livre>> addListLivre(@RequestBody List<Livre> livreList) {
        List<Livre> savedLivres = livreService.addListLivre(livreList);
        return ResponseEntity.ok(savedLivres);
    }

    @GetMapping("/fetchLivre")
    public ResponseEntity<List<Livre>>fetchLivre() {
        List<Livre> lives = livreService.fetchLivre();
        return ResponseEntity.ok(lives);
    }

    @PutMapping("updateLivre/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        Livre savedLivre = livreService.updateLivre(id, livre);
        return ResponseEntity.ok(savedLivre);
    }

    @DeleteMapping("/deleteLivre/{id}")
    public ResponseEntity<Livre> deleteLivre(@PathVariable Long id) {
        Optional<Livre> deletedLivre = livreService.deleteLivre(id);
        return deletedLivre
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
