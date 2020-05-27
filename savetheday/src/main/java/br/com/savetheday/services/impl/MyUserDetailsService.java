package br.com.savetheday.services.impl;

import br.com.savetheday.entities.Ong;
import br.com.savetheday.exceptions.EntidadeNaoEncontradaException;
import br.com.savetheday.repositories.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    OngRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Ong ong = repository.findByEmail(username);
        if(ong == null){
            throw new EntidadeNaoEncontradaException("Ong não Encontrada!");
        }
        return new User(ong.getEmail(), ong.getSenha(),new ArrayList<>());
    }
}
