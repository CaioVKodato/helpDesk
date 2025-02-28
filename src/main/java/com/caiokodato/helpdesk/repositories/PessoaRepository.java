package com.caiokodato.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiokodato.helpdesk.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
