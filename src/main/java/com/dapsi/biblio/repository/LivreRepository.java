package com.dapsi.biblio.repository;

import com.dapsi.biblio.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre,Long> {

    /**
     * Trouve tous les livres non supprimés logiquement
     * @return Liste des livres où isDeleted = false ou isDeleted = null
     */
    @Query("SELECT l FROM Livre l WHERE l.isDeleted = false OR l.isDeleted IS NULL")
    List<Livre> findByIsDeletedFalseOrIsDeletedIsNull();

    /**
     * Trouve un livre par ID s'il n'est pas supprimé logiquement
     * @param id L'identifiant du livre
     * @return Optional contenant le livre s'il existe et n'est pas supprimé
     */
    @Query("SELECT l FROM Livre l WHERE l.id = :id AND (l.isDeleted = false OR l.isDeleted IS NULL)")
    Optional<Livre> findByIdAndIsDeletedFalse(@Param("id") Long id);
}

