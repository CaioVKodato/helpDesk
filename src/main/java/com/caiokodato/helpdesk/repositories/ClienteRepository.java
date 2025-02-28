package com.caiokodato.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caiokodato.helpdesk.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
