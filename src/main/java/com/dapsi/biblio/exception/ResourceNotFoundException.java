package com.dapsi.biblio.exception;

/**
 * Exception personnalisée lancée lorsqu'une ressource demandée n'existe pas dans la base de données
 * Exemple : Etudiant avec un ID inexistant, Livre introuvable, etc.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructeur avec message personnalisé
     * @param message Le message d'erreur décrivant la ressource non trouvée
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause de l'exception
     * @param message Le message d'erreur
     * @param cause La cause originale de l'exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}