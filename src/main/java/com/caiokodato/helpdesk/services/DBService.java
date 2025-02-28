package com.caiokodato.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caiokodato.helpdesk.entities.Chamado;
import com.caiokodato.helpdesk.entities.Cliente;
import com.caiokodato.helpdesk.entities.Tecnico;
import com.caiokodato.helpdesk.enums.Perfil;
import com.caiokodato.helpdesk.enums.Prioridade;
import com.caiokodato.helpdesk.enums.Status;
import com.caiokodato.helpdesk.repositories.ChamadoRepository;
import com.caiokodato.helpdesk.repositories.ClienteRepository;
import com.caiokodato.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
    
    @Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
    
    public void instanciaDB() {
        Tecnico t1 = new Tecnico(null, "Valdir Cezar", "163.812.566-05", "valdir@gmail.com", "123");
		t1.addPerfil(Perfil.ADMIN);

		Cliente c1 = new Cliente(null, "Linus Torvalds", "705.116.440-13", "linus@gmail.com", "123");

		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado01", "Primeiro Chamado", t1, c1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
    }
}
