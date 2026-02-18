package com.dapsi.biblio.dto;

public class EmpruntRequest {
    private Long livreId;
    private Long etudiantId;

    public Long getLivreId() { return livreId; }
    public void setLivreId(Long livreId) { this.livreId = livreId; }

    public Long getEtudiantId() { return etudiantId; }
    public void setEtudiantId(Long etudiantId) { this.etudiantId = etudiantId; }
}
