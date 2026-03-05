package com.dapsi.biblio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestionnaire global des exceptions pour toute l'application
 * Intercepte toutes les exceptions lancées par les controllers et retourne des réponses standardisées
 * @RestControllerAdvice permet de centraliser la gestion des erreurs pour tous les controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les exceptions ResourceNotFoundException (ressource non trouvée)
     * Retourne un statut HTTP 404 NOT FOUND
     *
     * @param ex L'exception levée
     * @param request La requête HTTP qui a causé l'erreur
     * @return Une réponse contenant les détails de l'erreur
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Gère les exceptions BadRequestException (requête invalide)
     * Retourne un statut HTTP 400 BAD REQUEST
     *
     * @param ex L'exception levée
     * @param request La requête HTTP qui a causé l'erreur
     * @return Une réponse contenant les détails de l'erreur
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            BadRequestException ex,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère les erreurs de validation des données (annotations @Valid, @NotNull, etc.)
     * Retourne un statut HTTP 400 BAD REQUEST avec les détails des champs invalides
     *
     * @param ex L'exception de validation
     * @return Une réponse contenant la liste des erreurs de validation par champ
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        // Map pour stocker les erreurs : nom du champ -> message d'erreur
        Map<String, String> errors = new HashMap<>();

        // Parcourir toutes les erreurs de validation
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère toutes les autres exceptions non capturées par les handlers spécifiques
     * Retourne un statut HTTP 500 INTERNAL SERVER ERROR
     *
     * @param ex L'exception levée
     * @param request La requête HTTP qui a causé l'erreur
     * @return Une réponse contenant les détails de l'erreur
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}