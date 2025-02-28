package com.caiokodato.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiokodato.helpdesk.entities.Tecnico;
import com.caiokodato.helpdesk.repositories.TecnicoRepository;
import com.caiokodato.helpdesk.services.execptions.ObjectnotFoundExecption;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExecption("Objeto não encontrado! Id: "+ id));
    }
}
