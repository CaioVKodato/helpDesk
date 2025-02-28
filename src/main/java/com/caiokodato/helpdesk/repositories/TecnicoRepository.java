package com.caiokodato.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiokodato.helpdesk.entities.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {

}
