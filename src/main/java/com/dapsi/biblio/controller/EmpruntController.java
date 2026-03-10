package com.dapsi.biblio.controller;

import com.dapsi.biblio.model.Emprunt;
import com.dapsi.biblio.repository.EtudiantRepository;
import com.dapsi.biblio.repository.EmpruntRepository;
import com.dapsi.biblio.repository.LivreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("")
    public String addEmprunt() {

        return "ok";
    }




    @GetMapping("/emprunts")
    public List<Emprunt> getAll() {
        return empruntRepository.findByIsDeletedFalseOrIsDeletedIsNull();
    }

}
