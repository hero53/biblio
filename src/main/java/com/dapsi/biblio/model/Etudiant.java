package com.dapsi.biblio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name="Etudiants")

public class Etudiant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;

    @OneToMany(mappedBy = "etudiant")
    private List<Emprunt> emprunt;

}
