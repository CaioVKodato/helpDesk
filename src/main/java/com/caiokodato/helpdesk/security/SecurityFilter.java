package com.caiokodato.helpdesk.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.caiokodato.helpdesk.entities.Cliente;
import com.caiokodato.helpdesk.entities.Pessoa;
import com.caiokodato.helpdesk.entities.Tecnico;
import com.caiokodato.helpdesk.repositories.PessoaRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            Pessoa pessoa = pessoaRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("User Not Found"));

            
            String role = "ROLE_PESSOA";  
            if (pessoa instanceof Tecnico) {
                role = "ROLE_TECNICO";  
            }
            if (pessoa instanceof Cliente) {
                role = "ROLE_CLIENTE";
            }
            

            var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            var authentication = new UsernamePasswordAuthenticationToken(pessoa, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}