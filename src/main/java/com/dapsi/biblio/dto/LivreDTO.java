package com.dapsi.biblio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO (Data Transfer Object) pour l'entité Livre
 * Utilisé pour transférer les données entre le client et le serveur
 * Sépare la couche de présentation de la couche métier (entité JPA)
 * Permet de contrôler quelles données sont exposées et de valider les entrées
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreDTO {

    /**
     * Identifiant unique du livre
     * Non requis lors de la création (généré automatiquement)
     */
    private Long id;

    /**
     * Titre du livre
     * Champ obligatoire et ne peut pas être vide
     */
    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    /**
     * Auteur du livre
     * Champ obligatoire et ne peut pas être vide
     */
    @NotBlank(message = "L'auteur est obligatoire")
    private String auteur;

    /**
     * Description du livre
     * Champ optionnel
     */
    private String description;

    /**
     * Date de publication du livre
     * Champ obligatoire
     */
    @NotNull(message = "La date de publication est obligatoire")
    private Date datePublication;

    /**
     * Date de création du livre
     * Géré automatiquement par le système
     */
    private Date createdAt;

    /**
     * Date de dernière modification du livre
     * Géré automatiquement par le système
     */
    private Date updatedAt;

    /**
     * Date de suppression logique du livre
     * Null si le livre n'est pas supprimé
     */
    private Date deletedAt;

    /**
     * Indicateur de suppression logique
     * False ou null si le livre est actif
     */
    private Boolean isDeleted;

    // Note : On n'expose pas la liste des emprunts dans le DTO pour éviter les références circulaires
    // et pour ne pas surcharger les réponses API
}