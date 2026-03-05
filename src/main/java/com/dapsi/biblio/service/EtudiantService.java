package com.dapsi.biblio.service;

import com.dapsi.biblio.dto.EtudiantDTO;
import com.dapsi.biblio.exception.ResourceNotFoundException;
import com.dapsi.biblio.mapper.EtudiantMapper;
import com.dapsi.biblio.model.Etudiant;
import com.dapsi.biblio.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service métier pour la gestion des étudiants
 * Contient la logique métier et utilise les DTOs pour communiquer avec le controller
 * Utilise le mapper pour convertir entre entités et DTOs
 */
@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    /**
     * Constructeur avec injection de dépendances
     * @param etudiantRepository Le repository pour accéder aux données
     * @param etudiantMapper Le mapper pour convertir entités <-> DTOs
     */
    public EtudiantService(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
    }

    /**
     * Ajoute un nouvel étudiant dans la base de données
     * @param etudiantDTO Les données de l'étudiant à créer
     * @return Le DTO de l'étudiant créé avec son ID généré
     */
    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO) {
        // Convertir DTO -> Entity
        Etudiant etudiant = etudiantMapper.toEntity(etudiantDTO);

        // Sauvegarder en base
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        // Convertir Entity -> DTO et retourner
        return etudiantMapper.toDTO(savedEtudiant);
    }

    /**
     * Ajoute plusieurs étudiants en une seule opération
     * @param listEtudiantDTO La liste des étudiants à créer
     * @return La liste des étudiants créés avec leurs IDs générés
     */
    public List<EtudiantDTO> addListEtudiant(List<EtudiantDTO> listEtudiantDTO) {
        // Convertir la liste de DTOs en liste d'entités
        List<Etudiant> etudiants = etudiantMapper.toEntityList(listEtudiantDTO);

        // Sauvegarder tous les étudiants
        List<Etudiant> savedEtudiants = etudiantRepository.saveAll(etudiants);

        // Convertir et retourner la liste de DTOs
        return etudiantMapper.toDTOList(savedEtudiants);
    }

    /**
     * Récupère tous les étudiants de la base de données
     * @return La liste de tous les étudiants sous forme de DTOs
     */
    public List<EtudiantDTO> fetchEtudiant() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return etudiantMapper.toDTOList(etudiants);
    }

    /**
     * Met à jour les informations d'un étudiant existant
     * @param id L'identifiant de l'étudiant à modifier
     * @param etudiantDTO Les nouvelles données de l'étudiant
     * @return Le DTO de l'étudiant mis à jour
     * @throws ResourceNotFoundException Si l'étudiant n'existe pas
     */
    public EtudiantDTO updateEtudiant(Long id, EtudiantDTO etudiantDTO) {
        // Vérifier si l'étudiant existe
        Etudiant existingEtudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etudiant avec l'ID " + id + " n'existe pas"));

        // Mettre à jour les champs
        existingEtudiant.setNom(etudiantDTO.getNom());
        existingEtudiant.setPrenom(etudiantDTO.getPrenom());

        // Sauvegarder et retourner
        Etudiant updatedEtudiant = etudiantRepository.save(existingEtudiant);
        return etudiantMapper.toDTO(updatedEtudiant);
    }

    /**
     * Recherche un étudiant par son identifiant
     * @param id L'identifiant de l'étudiant recherché
     * @return Le DTO de l'étudiant trouvé
     * @throws ResourceNotFoundException Si l'étudiant n'existe pas
     */
    public EtudiantDTO findEtudiantByID(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etudiant avec l'ID " + id + " n'existe pas"));

        return etudiantMapper.toDTO(etudiant);
    }

    /**
     * Supprime un étudiant de la base de données
     * @param id L'identifiant de l'étudiant à supprimer
     * @throws ResourceNotFoundException Si l'étudiant n'existe pas
     */
    public void deleteEtudiant(Long id) {
        // Vérifier si l'étudiant existe avant de le supprimer
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Etudiant avec l'ID " + id + " n'existe pas"));

        // Supprimer l'étudiant
        etudiantRepository.delete(etudiant);
    }
}