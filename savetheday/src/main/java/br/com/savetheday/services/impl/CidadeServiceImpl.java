package br.com.savetheday.services.impl;

import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Estado;
import br.com.savetheday.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeServiceImpl {

    @Autowired
    private CidadeRepository repository;

    public Cidade defineCidade ( String nome, Estado estado) {
        Cidade cidade = repository.findByNomeAndEstado(nome, estado);
        if( cidade == null ){
            cidade = new Cidade(nome, estado);
            cidade = repository.save(cidade);
        }
        return cidade;
    }

}
