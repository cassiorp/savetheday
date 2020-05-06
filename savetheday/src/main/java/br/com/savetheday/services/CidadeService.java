package br.com.savetheday.services;


import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Estado;
import br.com.savetheday.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade defineCidade ( String nome, Estado estado) {
        Cidade cidade = repository.findByNomeAndEstado(nome, estado);
        if( cidade == null ){
            cidade = new Cidade(null,nome, estado );
            cidade = repository.save(cidade);
        }
        return cidade;
    }

}
