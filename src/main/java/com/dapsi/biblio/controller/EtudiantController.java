package com.dapsi.biblio.controller;

import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @PostMapping("/addEtudiant")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant){
        Etudiant saveEtudiant = etudiantRepository.save(etudiant);
        return ResponseEntity.ok(saveEtudiant);
    }

    @PostMapping("/addListEtudiant")
    public List<Etudiant> addListEtudiant(@RequestBody List<Etudiant> listEtudiant){
        return etudiantRepository.saveAll(listEtudiant);
    }

    @GetMapping("/fetchEtudiant")
    public ResponseEntity<List<Etudiant>> fetchEtudiant(){
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return ResponseEntity.ok(etudiants);
    }

    @PutMapping("/updateEtudiant/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiant){
        Etudiant isExist = etudiantRepository.findById(id).orElse(null);
        if(isExist != null){
            isExist.setNom(etudiant.getNom());
            isExist.setPrenom(etudiant.getPrenom());
            Etudiant updateEtudiant =  etudiantRepository.save(isExist);
            return ResponseEntity.ok(updateEtudiant);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/findEtudiantByID/{id}")
    public ResponseEntity<Etudiant> findUserByID(@PathVariable Long id){
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if(etudiant != null){
            return ResponseEntity.ok(etudiant);
        }else  {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteEtudiant/{id}")
    public ResponseEntity<Etudiant> deleteEtudiant(@PathVariable Long id){
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if(etudiant != null){
            etudiantRepository.delete(etudiant);
        }else  {
            return ResponseEntity.notFound().build();
        }
        return null;
    }
}
