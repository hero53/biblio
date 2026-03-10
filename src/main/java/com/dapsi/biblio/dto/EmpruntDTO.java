package com.dapsi.biblio.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpruntDTO {
    private Long id;

    @NotBlank(message = "L'étudiant est obligatoire")
    private Long etudiant_id;

    @NotBlank(message = "Le livre est obligatoire")
    private Long livre_id;

    private Date created_at;
    private Date deleted_at;
    private Date updated_at;
    private Date date_emprunt;
    private Date date_retoure;

    private Boolean is_deleted;


}
