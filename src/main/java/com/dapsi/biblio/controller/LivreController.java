package com.dapsi.biblio.controller;


import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PostMapping("/addLivre")
    @ResponseStatus(HttpStatus.CREATED)
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.createLivre(livre);
    }

    @PostMapping("/addListLivre")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Livre> addListLivre(@RequestBody List<Livre> livreList) {
        return livreService.addListLivre(livreList);
    }

    @GetMapping("/fetchLivre")
    @ResponseStatus(HttpStatus.OK)
    public List<Livre> fetchLivre() {
        return livreService.fetchLivre();
    }

    @PutMapping("updateLivre/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        return livreService.updateLivre(id, livre);
    }

    @DeleteMapping("/deleteLivre/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Livre deleteLivre(@PathVariable Long id) {
        return livreService.deleteLivre(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre not found"));
    }


}
