package com.dapsi.biblio.service;

import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public List<Etudiant> addListEtudiant(List<Etudiant> listEtudiant) {
        return etudiantRepository.saveAll(listEtudiant);
    }

    public List<Etudiant> fetchEtudiant() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> updateEtudiant(Long id, Etudiant etudiant) {
        return etudiantRepository.findById(id)
                .map(existingEtudiant -> {
                    existingEtudiant.setNom(etudiant.getNom());
                    existingEtudiant.setPrenom(etudiant.getPrenom());
                    return etudiantRepository.save(existingEtudiant);
                });
    }

    public Optional<Etudiant> findEtudiantByID(Long id) {
        return etudiantRepository.findById(id);
    }

    public boolean deleteEtudiant(Long id) {
        return etudiantRepository.findById(id)
                .map(etudiant -> {
                    etudiantRepository.delete(etudiant);
                    return true;
                })
                .orElse(false);
    }
}