package com.caiokodato.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiokodato.helpdesk.entities.Pessoa;
import com.caiokodato.helpdesk.entities.Tecnico;
import com.caiokodato.helpdesk.entities.dtos.TecnicoDTO;
import com.caiokodato.helpdesk.repositories.PessoaRepository;
import com.caiokodato.helpdesk.repositories.TecnicoRepository;
import com.caiokodato.helpdesk.services.execptions.DataIntegrityViolationException;
import com.caiokodato.helpdesk.services.execptions.ObjectnotFoundExecption;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExecption("Objeto não encontrado! Id: "+ id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
                Tecnico newObj = new Tecnico(objDTO);
                return tecnicoRepository.save(newObj);
            }
        
     private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF ja cadastrado na base de dados!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail ja cadastrado na base de dados!");
        }
    }

    public Tecnico update(Integer id,@Valid TecnicoDTO objDTO) {
      objDTO.setId(id);
      Tecnico oldObj = findById(id);
      validaPorCpfEEmail(objDTO);
      oldObj = new Tecnico(objDTO);
      return tecnicoRepository.save(oldObj);
    }

   
}
