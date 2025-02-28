package com.caiokodato.helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caiokodato.helpdesk.entities.Tecnico;
import com.caiokodato.helpdesk.entities.dtos.TecnicoDTO;
import com.caiokodato.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

}
