package com.dapsi.biblio.controller;

import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping("/addEtudiant")
    @ResponseStatus(HttpStatus.CREATED)
    public Etudiant addEtudiant(@Valid @RequestBody Etudiant etudiant){
        return etudiantService.addEtudiant(etudiant);
    }

    @PostMapping("/addListEtudiant")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Etudiant> addListEtudiant(@Valid @RequestBody List<Etudiant> listEtudiant){
        return etudiantService.addListEtudiant(listEtudiant);
    }

    @GetMapping("/fetchEtudiant")
    @ResponseStatus(HttpStatus.OK)
    public List<Etudiant> fetchEtudiant(){
        return etudiantService.fetchEtudiant();
    }

    @PutMapping("/updateEtudiant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Etudiant updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiant etudiant){
        return etudiantService.updateEtudiant(id, etudiant)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Etudiant not found"));
    }

    @GetMapping("/findEtudiantByID/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Etudiant findEtudiantByID(@PathVariable Long id){
        return etudiantService.findEtudiantByID(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Etudiant not found"));
    }

    @DeleteMapping("/deleteEtudiant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEtudiant(@PathVariable Long id){
        boolean deleted = etudiantService.deleteEtudiant(id);
        if(!deleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etudiant not found");
        }
    }
}
