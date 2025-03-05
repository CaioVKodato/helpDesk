package com.caiokodato.helpdesk.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.caiokodato.helpdesk.entities.Pessoa;
import com.caiokodato.helpdesk.repositories.PessoaRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired
    private PessoaRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa pessoa = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));
         return new org.springframework.security.core.userdetails.User(pessoa.getEmail(), pessoa.getSenha(), new ArrayList<>());

       
    }
}