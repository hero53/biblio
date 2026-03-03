package com.dapsi.biblio.controller;

import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Etudiant> addEtudiant(@Valid @RequestBody Etudiant etudiant){
        try {
            Etudiant saveEtudiant = etudiantService.addEtudiant(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveEtudiant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addListEtudiant")
    public ResponseEntity<List<Etudiant>> addListEtudiant(@Valid @RequestBody List<Etudiant> listEtudiant){
        try {
            List<Etudiant> savedEtudiants = etudiantService.addListEtudiant(listEtudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEtudiants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fetchEtudiant")
    public ResponseEntity<List<Etudiant>> fetchEtudiant(){
        try {
            List<Etudiant> etudiants = etudiantService.fetchEtudiant();
            return ResponseEntity.ok(etudiants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateEtudiant/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiant etudiant){
        try {
            return etudiantService.updateEtudiant(id, etudiant)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findEtudiantByID/{id}")
    public ResponseEntity<Etudiant> findEtudiantByID(@PathVariable Long id){
        try {
            return etudiantService.findEtudiantByID(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//a
    @DeleteMapping("/deleteEtudiant/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id){
        try {
            boolean deleted = etudiantService.deleteEtudiant(id);
            if(deleted){
                return ResponseEntity.noContent().build();
            }else  {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
