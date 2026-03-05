package com.dapsi.biblio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) pour l'entité Etudiant
 * Utilisé pour transférer les données entre le client et le serveur
 * Sépare la couche de présentation de la couche métier (entité JPA)
 * Permet de contrôler quelles données sont exposées et de valider les entrées
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO {

    /**
     * Identifiant unique de l'étudiant
     * Non requis lors de la création (généré automatiquement)
     */
    private Long id;

    /**
     * Nom de famille de l'étudiant
     * Champ obligatoire et ne peut pas être vide
     */
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    /**
     * Prénom de l'étudiant
     * Champ obligatoire et ne peut pas être vide
     */
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    // Note : On n'expose pas la liste des emprunts dans le DTO pour éviter les références circulaires
    // et pour ne pas surcharger les réponses API
}