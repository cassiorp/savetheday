package br.com.savetheday.services.impl;

import br.com.savetheday.entities.Estado;
import br.com.savetheday.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoServiceImpl {

    @Autowired
    private EstadoRepository repository;

    public Estado defineEstado( String nome ){
        Estado estado = repository.findByNome(nome);
        if(estado == null){
            estado = new Estado(nome);
            repository.save(estado);
        }
        return  estado;
    }
}
