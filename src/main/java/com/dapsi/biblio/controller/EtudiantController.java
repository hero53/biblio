package com.dapsi.biblio.controller;

import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @PostMapping("/addEtudiant")
    public ResponseEntity<Etudiant> addEtudiant(@Valid @RequestBody Etudiant etudiant){
        try {
            Etudiant saveEtudiant = etudiantRepository.save(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveEtudiant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addListEtudiant")
    public ResponseEntity<List<Etudiant>> addListEtudiant(@Valid @RequestBody List<Etudiant> listEtudiant){
        try {
            List<Etudiant> savedEtudiants = etudiantRepository.saveAll(listEtudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEtudiants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fetchEtudiant")
    public ResponseEntity<List<Etudiant>> fetchEtudiant(){
        try {
            List<Etudiant> etudiants = etudiantRepository.findAll();
            return ResponseEntity.ok(etudiants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateEtudiant/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @Valid @RequestBody Etudiant etudiant){
        try {
            Etudiant isExist = etudiantRepository.findById(id).orElse(null);
            if(isExist != null){
                isExist.setNom(etudiant.getNom());
                isExist.setPrenom(etudiant.getPrenom());
                Etudiant updateEtudiant =  etudiantRepository.save(isExist);
                return ResponseEntity.ok(updateEtudiant);
            }else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findEtudiantByID/{id}")
    public ResponseEntity<Etudiant> findEtudiantByID(@PathVariable Long id){
        try {
            Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
            if(etudiant != null){
                return ResponseEntity.ok(etudiant);
            }else  {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//a
    @DeleteMapping("/deleteEtudiant/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id){
        try {
            Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
            if(etudiant != null){
                etudiantRepository.delete(etudiant);
                return ResponseEntity.noContent().build();
            }else  {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
