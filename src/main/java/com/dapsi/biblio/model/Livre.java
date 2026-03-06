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
    private String titre;
    private String auteur;
    private String description;
    private Date datePublication;

    @OneToMany(mappedBy = "livre")
    private List<Emprunt> emprunt;

}
