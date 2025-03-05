package com.caiokodato.helpdesk.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caiokodato.helpdesk.controllers.dto.LoginRequestDTO;
import com.caiokodato.helpdesk.controllers.dto.ResponseDTO;
import com.caiokodato.helpdesk.entities.Pessoa;
import com.caiokodato.helpdesk.repositories.PessoaRepository;
import com.caiokodato.helpdesk.security.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Pessoa pessoa = this.pessoaRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), pessoa.getSenha())) {
            String token = this.tokenService.generateToken(pessoa);
            return ResponseEntity.ok(new ResponseDTO(pessoa.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}
