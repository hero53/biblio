package com.dapsi.biblio.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity


public class LivreEtudiant {
    @EmbeddedId
    private LivreEtudiant id;

    @ManyToOne
    @MapsId
    private Livre livre;

    @ManyToOne
    @MapsId
    private Etudiant etudiant;

    public Date dateEmprunt;
    private Date dateRetoure;

    @PrePersist
    public void prePersist() {
        if (dateEmprunt == null) {
            dateEmprunt = new Date();
        }
    }
}
