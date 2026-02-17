package com.dapsi.biblio.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="Etudiants")

public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
}
