package com.dapsi.biblio.service;

import com.dapsi.biblio.mapper.EtudiantMapper;
import com.dapsi.biblio.mapper.LivreMapper;
import com.dapsi.biblio.repository.EmpruntRepository;

public class EmpruntService {
    final EmpruntRepository empruntRepository;
    final EtudiantMapper etudiantMapper;
    final LivreMapper livreMapper;

    public EmpruntService(EmpruntRepository empruntRepository, EtudiantMapper  etudiantMapper, LivreMapper livreMapper) {
        this.empruntRepository = empruntRepository;
        this.etudiantMapper = etudiantMapper;
        this.livreMapper = livreMapper;
    }

    publ;


}
