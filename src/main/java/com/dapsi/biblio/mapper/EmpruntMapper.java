package com.dapsi.biblio.mapper;
import com.dapsi.biblio.dto.EmpruntDTO;
import com.dapsi.biblio.model.Emprunt;
import org.springframework.stereotype.Component;



@Component
public class EmpruntMapper {
     public EmpruntDTO toEmpruntDTO(Emprunt emprunt) {
         if (emprunt == null) {
             return null;
         }
         EmpruntDTO empruntDTO = new EmpruntDTO();
         empruntDTO.setId(emprunt.getId());
         empruntDTO.set

     }
}
