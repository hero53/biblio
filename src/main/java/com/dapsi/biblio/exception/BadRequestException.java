package com.dapsi.biblio.exception;

/**
 * Exception personnalisée lancée lorsqu'une requête contient des données invalides
 * Exemple : Données manquantes, format incorrect, contraintes de validation non respectées
 */
public class BadRequestException extends RuntimeException {

    /**
     * Constructeur avec message personnalisé
     * @param message Le message d'erreur décrivant le problème avec la requête
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause de l'exception
     * @param message Le message d'erreur
     * @param cause La cause originale de l'exception
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}