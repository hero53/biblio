package com.dapsi.biblio.repository;

import com.dapsi.biblio.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpruntRepository extends JpaRepository<Emprunt,Long> {

    /**
     * Trouve tous les emprunts non supprimés logiquement
     * @return Liste des emprunts où isDeleted = false ou isDeleted = null
     */
    @Query("SELECT e FROM Emprunt e WHERE e.isDeleted = false OR e.isDeleted IS NULL")
    List<Emprunt> findByIsDeletedFalseOrIsDeletedIsNull();

    /**
     * Trouve un emprunt par ID s'il n'est pas supprimé logiquement
     * @param id L'identifiant de l'emprunt
     * @return Optional contenant l'emprunt s'il existe et n'est pas supprimé
     */
    @Query("SELECT e FROM Emprunt e WHERE e.id = :id AND (e.isDeleted = false OR e.isDeleted IS NULL)")
    Optional<Emprunt> findByIdAndIsDeletedFalse(@Param("id") Long id);
}
