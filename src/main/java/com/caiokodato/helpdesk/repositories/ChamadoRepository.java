package com.caiokodato.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiokodato.helpdesk.entities.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {

}
