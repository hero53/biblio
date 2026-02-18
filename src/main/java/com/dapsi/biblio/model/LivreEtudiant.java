package com.dapsi.biblio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class LivreEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livre livre;

    @ManyToOne
    private Etudiant etudiant;

    public Date dateEmprunt;

    @Column(nullable = true)
    private Date dateRetoure;

    @PrePersist
    public void prePersist() {
        if (dateEmprunt == null) {
            dateEmprunt = new Date();
        }
    }

}
