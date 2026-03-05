package com.dapsi.biblio.controller;


import com.dapsi.biblio.dto.LivreDTO;
import com.dapsi.biblio.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller REST pour la gestion des livres
 * Expose les endpoints de l'API pour les opérations CRUD sur les livres
 * Utilise les DTOs pour séparer la couche de présentation de la couche métier
 */
@RestController
@RequestMapping("/api/livre")
public class LivreController {

    private final LivreService livreService;

    /**
     * Constructeur avec injection de dépendance du service
     * @param livreService Le service métier pour les livres
     */
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    /**
     * Endpoint pour créer un nouveau livre
     * POST /api/livre/addLivre
     *
     * @param livreDTO Les données du livre à créer (validées automatiquement)
     * @return Le livre créé avec son ID généré
     */
    @PostMapping("/addLivre")
    @ResponseStatus(HttpStatus.CREATED)
    public LivreDTO createLivre(@Valid @RequestBody LivreDTO livreDTO) {
        return livreService.createLivre(livreDTO);
    }

    /**
     * Endpoint pour créer plusieurs livres en une seule requête
     * POST /api/livre/addListLivre
     *
     * @param livreListDTO La liste des livres à créer (validés automatiquement)
     * @return La liste des livres créés avec leurs IDs générés
     */
    @PostMapping("/addListLivre")
    @ResponseStatus(HttpStatus.CREATED)
    public List<LivreDTO> addListLivre(@Valid @RequestBody List<LivreDTO> livreListDTO) {
        return livreService.addListLivre(livreListDTO);
    }

    /**
     * Endpoint pour récupérer tous les livres
     * GET /api/livre/fetchLivre
     *
     * @return La liste de tous les livres
     */
    @GetMapping("/fetchLivre")
    @ResponseStatus(HttpStatus.OK)
    public List<LivreDTO> fetchLivre() {
        return livreService.fetchLivre();
    }

    /**
     * Endpoint pour mettre à jour un livre existant
     * PUT /api/livre/updateLivre/{id}
     *
     * @param id L'identifiant du livre à modifier
     * @param livreDTO Les nouvelles données du livre (validées automatiquement)
     * @return Le livre mis à jour
     * @throws com.dapsi.biblio.exception.ResourceNotFoundException Si le livre n'existe pas
     */
    @PutMapping("/updateLivre/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivreDTO updateLivre(@PathVariable Long id, @Valid @RequestBody LivreDTO livreDTO) {
        return livreService.updateLivre(id, livreDTO);
    }

    /**
     * Endpoint pour supprimer un livre et retourner ses données
     * DELETE /api/livre/deleteLivre/{id}
     *
     * @param id L'identifiant du livre à supprimer
     * @return Le livre supprimé
     * @throws com.dapsi.biblio.exception.ResourceNotFoundException Si le livre n'existe pas
     */
    @DeleteMapping("/deleteLivre/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivreDTO deleteLivre(@PathVariable Long id) {
        return livreService.deleteLivre(id);
    }

    /**
     * Endpoint pour rechercher un livre par son ID
     * GET /api/livre/findById/{id}
     *
     * @param id L'identifiant du livre recherché
     * @return Le livre trouvé
     * @throws com.dapsi.biblio.exception.ResourceNotFoundException Si le livre n'existe pas
     */
    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivreDTO findById(@PathVariable Long id) {
        return livreService.findById(id);
    }


}
