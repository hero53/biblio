package com.dapsi.biblio.repository;

import com.dapsi.biblio.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    /**
     * Trouve tous les étudiants non supprimés logiquement
     * @return Liste des étudiants où isDeleted = false ou isDeleted = null
     */
    @Query("SELECT e FROM Etudiant e WHERE e.isDeleted = false OR e.isDeleted IS NULL")
    List<Etudiant> findByIsDeletedFalseOrIsDeletedIsNull();

    /**
     * Trouve un étudiant par ID s'il n'est pas supprimé logiquement
     * @param id L'identifiant de l'étudiant
     * @return Optional contenant l'étudiant s'il existe et n'est pas supprimé
     */
    @Query("SELECT e FROM Etudiant e WHERE e.id = :id AND (e.isDeleted = false OR e.isDeleted IS NULL)")
    Optional<Etudiant> findByIdAndIsDeletedFalse(@Param("id") Long id);
}

