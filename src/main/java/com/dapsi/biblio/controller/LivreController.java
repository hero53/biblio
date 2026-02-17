package com.dapsi.biblio.controller;


import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @PostMapping("/addLivre")
    public ResponseEntity<Livre> addLivre(@RequestBody Livre livre) {
        Livre savedLivre = livreRepository.save(livre);
        return ResponseEntity.ok(savedLivre);
    }
