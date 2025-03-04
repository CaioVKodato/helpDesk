package com.caiokodato.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiokodato.helpdesk.services.execptions.ObjectnotFoundExecption;
import com.caiokodato.helpdesk.entities.Chamado;
import com.caiokodato.helpdesk.repositories.ChamadoRepository;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById (Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExecption("Objeto não encontrado ! ID: " + id)) ;
    }
}
