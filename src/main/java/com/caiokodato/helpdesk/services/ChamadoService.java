package com.caiokodato.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiokodato.helpdesk.services.execptions.ObjectnotFoundExecption;


import com.caiokodato.helpdesk.entities.Chamado;
import com.caiokodato.helpdesk.entities.Cliente;
import com.caiokodato.helpdesk.entities.Tecnico;
import com.caiokodato.helpdesk.entities.dtos.ChamadoDTO;
import com.caiokodato.helpdesk.enums.Prioridade;
import com.caiokodato.helpdesk.enums.Status;
import com.caiokodato.helpdesk.repositories.ChamadoRepository;

@Service
public class ChamadoService {
    
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById (Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExecption("Objeto não encontrado ! ID: " + id)) ;
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        
        return chamado;
    }
    
}
