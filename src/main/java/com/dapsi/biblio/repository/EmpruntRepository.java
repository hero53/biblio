package com.dapsi.biblio.repository;

import com.dapsi.biblio.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt,Long> {

}
