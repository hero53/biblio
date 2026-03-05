package com.dapsi.biblio.mapper;

import com.dapsi.biblio.dto.LivreDTO;
import com.dapsi.biblio.model.Livre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre l'entité Livre et le DTO LivreDTO
 * Utilise le pattern Mapper pour séparer la logique de conversion
 * @Component permet à Spring d'injecter cette classe comme un bean
 */
@Component
public class LivreMapper {

    /**
     * Convertit une entité Livre en LivreDTO
     * Utilisé pour les réponses API (Entity -> DTO)
     *
     * @param livre L'entité Livre à convertir
     * @return Le DTO correspondant, ou null si l'entité est null
     */
    public LivreDTO toDTO(Livre livre) {
        if (livre == null) {
            return null;
        }

        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setAuteur(livre.getAuteur());
        dto.setDescription(livre.getDescription());
        dto.setDatePublication(livre.getDatePublication());

        return dto;
    }

    /**
     * Convertit un LivreDTO en entité Livre
     * Utilisé pour les requêtes entrantes (DTO -> Entity)
     *
     * @param dto Le DTO à convertir
     * @return L'entité Livre correspondante, ou null si le DTO est null
     */
    public Livre toEntity(LivreDTO dto) {
        if (dto == null) {
            return null;
        }

        Livre livre = new Livre();
        livre.setId(dto.getId());
        livre.setTitre(dto.getTitre());
        livre.setAuteur(dto.getAuteur());
        livre.setDescription(dto.getDescription());
        livre.setDatePublication(dto.getDatePublication());

        return livre;
    }

    /**
     * Convertit une liste d'entités Livre en liste de LivreDTO
     * Utile pour les endpoints qui retournent plusieurs livres
     *
     * @param livres La liste d'entités à convertir
     * @return La liste de DTOs correspondante
     */
    public List<LivreDTO> toDTOList(List<Livre> livres) {
        if (livres == null) {
            return null;
        }

        return livres.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de LivreDTO en liste d'entités Livre
     * Utile pour les endpoints qui acceptent plusieurs livres
     *
     * @param dtos La liste de DTOs à convertir
     * @return La liste d'entités correspondante
     */
    public List<Livre> toEntityList(List<LivreDTO> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}