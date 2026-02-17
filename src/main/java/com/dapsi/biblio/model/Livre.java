package com.dapsi.biblio.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Entity
@Data
@Table(name="Livres")

public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titre;
    private String auteur;
    private String description;
    private Date datePublication;

}
