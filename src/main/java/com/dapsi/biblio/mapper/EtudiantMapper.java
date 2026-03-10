package com.dapsi.biblio.mapper;

import com.dapsi.biblio.dto.EtudiantDTO;
import com.dapsi.biblio.model.Etudiant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre l'entité Etudiant et le DTO EtudiantDTO
 * Utilise le pattern Mapper pour séparer la logique de conversion
 * @Component permet à Spring d'injecter cette classe comme un bean
 */
@Component
public class EtudiantMapper {

    /**
     * Convertit une entité Etudiant en EtudiantDTO
     * Utilisé pour les réponses API (Entity -> DTO)
     *
     * @param etudiant L'entité Etudiant à convertir
     * @return Le DTO correspondant, ou null si l'entité est null
     */
    public EtudiantDTO toDTO(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }

        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());

        // Mapping des champs du BaseEntity
        dto.setCreatedAt(etudiant.getCreatedAt());
        dto.setUpdatedAt(etudiant.getUpdatedAt());
        dto.setDeletedAt(etudiant.getDeletedAt());
        dto.setIsDeleted(etudiant.getIsDeleted());

        return dto;
    }

    /**
     * Convertit un EtudiantDTO en entité Etudiant
     * Utilisé pour les requêtes entrantes (DTO -> Entity)
     * Note: Les champs du BaseEntity (createdAt, updatedAt, deletedAt, isDeleted)
     * ne sont pas mappés car ils sont gérés automatiquement par le système
     *
     * @param dto Le DTO à convertir
     * @return L'entité Etudiant correspondante, ou null si le DTO est null
     */
    public Etudiant toEntity(EtudiantDTO dto) {
        if (dto == null) {
            return null;
        }

        Etudiant etudiant = new Etudiant();
        etudiant.setId(dto.getId());
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());

        return etudiant;
    }

    /**
     * Convertit une liste d'entités Etudiant en liste de EtudiantDTO
     * Utile pour les endpoints qui retournent plusieurs étudiants
     *
     * @param etudiants La liste d'entités à convertir
     * @return La liste de DTOs correspondante
     */
    public List<EtudiantDTO> toDTOList(List<Etudiant> etudiants) {
        if (etudiants == null) {
            return null;
        }

        return etudiants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de EtudiantDTO en liste d'entités Etudiant
     * Utile pour les endpoints qui acceptent plusieurs étudiants
     *
     * @param dtos La liste de DTOs à convertir
     * @return La liste d'entités correspondante
     */
    public List<Etudiant> toEntityList(List<EtudiantDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}