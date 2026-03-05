package com.dapsi.biblio.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe représentant la structure de réponse d'erreur standardisée pour l'API
 * Toutes les erreurs seront retournées dans ce format pour assurer la cohérence
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * Le timestamp (date et heure) de l'erreur
     */
    private LocalDateTime timestamp;

    /**
     * Le code de statut HTTP (404, 400, 500, etc.)
     */
    private int status;

    /**
     * Le nom du statut HTTP (Not Found, Bad Request, etc.)
     */
    private String error;

    /**
     * Le message d'erreur détaillé expliquant le problème
     */
    private String message;

    /**
     * Le chemin de l'endpoint où l'erreur s'est produite
     */
    private String path;
}