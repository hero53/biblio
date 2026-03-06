package com.dapsi.biblio.service;

import com.dapsi.biblio.dto.LivreDTO;
import com.dapsi.biblio.exception.ResourceNotFoundException;
import com.dapsi.biblio.mapper.LivreMapper;
import com.dapsi.biblio.model.Livre;
import com.dapsi.biblio.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service métier pour la gestion des livres
 * Contient la logique métier et utilise les DTOs pour communiquer avec le controller
 * Utilise le mapper pour convertir entre entités et DTOs
 */
@Service
public class LivreService {

    private final LivreRepository livreRepository;
    private final LivreMapper livreMapper;

    /**
     * Constructeur avec injection de dépendances
     * @param livreRepository Le repository pour accéder aux données
     * @param livreMapper Le mapper pour convertir entités <-> DTOs
     */
    public LivreService(LivreRepository livreRepository, LivreMapper livreMapper) {
        this.livreRepository = livreRepository;
        this.livreMapper = livreMapper;
    }

    /**
     * Crée un nouveau livre dans la base de données
     * @param livreDTO Les données du livre à créer
     * @return Le DTO du livre créé avec son ID généré
     */
    public LivreDTO createLivre(LivreDTO livreDTO) {
        // Convertir DTO -> Entity
        Livre livre = livreMapper.toEntity(livreDTO);

        // Sauvegarder en base
        Livre savedLivre = livreRepository.save(livre);

        // Convertir Entity -> DTO et retourner
        return livreMapper.toDTO(savedLivre);
    }

    /**
     * Ajoute plusieurs livres en une seule opération
     * @param livreListDTO La liste des livres à créer
     * @return La liste des livres créés avec leurs IDs générés
     */
    public List<LivreDTO> addListLivre(List<LivreDTO> livreListDTO) {
        // Convertir la liste de DTOs en liste d'entités
        List<Livre> livres = livreMapper.toEntityList(livreListDTO);

        // Sauvegarder tous les livres
        List<Livre> savedLivres = livreRepository.saveAll(livres);

        // Convertir et retourner la liste de DTOs
        return livreMapper.toDTOList(savedLivres);
    }

    /**
     * Récupère tous les livres non supprimés de la base de données
     * @return La liste de tous les livres actifs sous forme de DTOs
     */
    public List<LivreDTO> fetchLivre() {
        List<Livre> livres = livreRepository.findByIsDeletedFalseOrIsDeletedIsNull();
        return livreMapper.toDTOList(livres);
    }

    /**
     * Met à jour les informations d'un livre existant
     * @param id L'identifiant du livre à modifier
     * @param livreDTO Les nouvelles données du livre
     * @return Le DTO du livre mis à jour
     * @throws ResourceNotFoundException Si le livre n'existe pas ou est supprimé
     */
    public LivreDTO updateLivre(Long id, LivreDTO livreDTO) {
        // Vérifier si le livre existe et n'est pas supprimé
        Livre existingLivre = livreRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre avec l'ID " + id + " n'existe pas"));

        // Mettre à jour les champs
        existingLivre.setTitre(livreDTO.getTitre());
        existingLivre.setAuteur(livreDTO.getAuteur());
        existingLivre.setDescription(livreDTO.getDescription());
        existingLivre.setDatePublication(livreDTO.getDatePublication());

        // Sauvegarder et retourner
        Livre updatedLivre = livreRepository.save(existingLivre);
        return livreMapper.toDTO(updatedLivre);
    }

    /**
     * Supprime logiquement un livre de la base de données
     * @param id L'identifiant du livre à supprimer
     * @return Le DTO du livre supprimé
     * @throws ResourceNotFoundException Si le livre n'existe pas ou est déjà supprimé
     */
    public LivreDTO deleteLivre(Long id) {
        // Vérifier si le livre existe et n'est pas déjà supprimé
        Livre livre = livreRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre avec l'ID " + id + " n'existe pas"));

        // Suppression logique
        livre.setIsDeleted(true);
        livre.setDeletedAt(new Date());

        // Sauvegarder les modifications
        Livre deletedLivre = livreRepository.save(livre);

        // Retourner le DTO du livre supprimé
        return livreMapper.toDTO(deletedLivre);
    }

    /**
     * Recherche un livre par son identifiant
     * @param id L'identifiant du livre recherché
     * @return Le DTO du livre trouvé
     * @throws ResourceNotFoundException Si le livre n'existe pas ou est supprimé
     */
    public LivreDTO findById(Long id) {
        Livre livre = livreRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre avec l'ID " + id + " n'existe pas"));

        return livreMapper.toDTO(livre);
    }
}

