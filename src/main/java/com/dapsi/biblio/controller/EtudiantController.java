package com.dapsi.biblio.controller;

import com.dapsi.biblio.dto.EtudiantDTO;
import com.dapsi.biblio.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller REST pour la gestion des étudiants
 * Expose les endpoints de l'API pour les opérations CRUD sur les étudiants
 * Utilise les DTOs pour séparer la couche de présentation de la couche métier
 */
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    /**
     * Constructeur avec injection de dépendance du service
     * @param etudiantService Le service métier pour les étudiants
     */
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    /**
     * Endpoint pour créer un nouvel étudiant
     * POST /api/etudiants/addEtudiant
     *
     * @param etudiantDTO Les données de l'étudiant à créer (validées automatiquement)
     * @return L'étudiant créé avec son ID généré
     */
    @PostMapping("/addEtudiant")
    @ResponseStatus(HttpStatus.CREATED)
    public EtudiantDTO addEtudiant(@Valid @RequestBody EtudiantDTO etudiantDTO){
        return etudiantService.addEtudiant(etudiantDTO);
    }

    /**
     * Endpoint pour créer plusieurs étudiants en une seule requête
     * POST /api/etudiants/addListEtudiant
     *
     * @param listEtudiantDTO La liste des étudiants à créer (validés automatiquement)
     * @return La liste des étudiants créés avec leurs IDs générés
     */
    @PostMapping("/addListEtudiant")
    @ResponseStatus(HttpStatus.CREATED)
    public List<EtudiantDTO> addListEtudiant(@Valid @RequestBody List<EtudiantDTO> listEtudiantDTO){
        return etudiantService.addListEtudiant(listEtudiantDTO);
    }

    /**
     * Endpoint pour récupérer tous les étudiants
     * GET /api/etudiants/fetchEtudiant
     *
     * @return La liste de tous les étudiants
     */
    @GetMapping("/fetchEtudiant")
    @ResponseStatus(HttpStatus.OK)
    public List<EtudiantDTO> fetchEtudiant(){
        return etudiantService.fetchEtudiant();
    }

    /**
     * Endpoint pour mettre à jour un étudiant existant
     * PUT /api/etudiants/updateEtudiant/{id}
     *
     * @param id L'identifiant de l'étudiant à modifier
     * @param etudiantDTO Les nouvelles données de l'étudiant (validées automatiquement)
     * @return L'étudiant mis à jour
     * @throws com.dapsi.biblio.exception.ResourceNotFoundException Si l'étudiant n'existe pas
     */
    @PutMapping("/updateEtudiant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EtudiantDTO updateEtudiant(@PathVariable Long id, @Valid @RequestBody EtudiantDTO etudiantDTO){
        return etudiantService.updateEtudiant(id, etudiantDTO);
    }

    /**
     * Endpoint pour rechercher un étudiant par son ID
     * GET /api/etudiants/findEtudiantByID/{id}
     *
     * @param id L'identifiant de l'étudiant recherché
     * @return L'étudiant trouvé
     * @throws com.dapsi.biblio.exception.ResourceNotFoundException Si l'étudiant n'existe pas
     */
    @GetMapping("/findEtudiantByID/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EtudiantDTO findEtudiantByID(@PathVariable Long id){
        return etudiantService.findEtudiantByID(id);
    }

    /**
     * Endpoint pour supprimer un étudiant
     * DELETE /api/etudiants/deleteEtudiant/{id}
     *
     * @param id L'identifiant de l'étudiant à supprimer
     * @throws com.dapsi.biblio.exception.ResourceNotFoundException Si l'étudiant n'existe pas
     */
    @DeleteMapping("/deleteEtudiant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEtudiant(@PathVariable Long id){
        etudiantService.deleteEtudiant(id);
    }
}
