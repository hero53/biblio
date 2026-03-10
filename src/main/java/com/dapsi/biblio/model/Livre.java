package com.dapsi.biblio.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name="Livres")

public class Livre extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Date datePublication;

    @OneToMany(mappedBy = "livre")
    private List<Emprunt> emprunt;

}
