package br.com.savetheday.repositories;

import br.com.savetheday.entities.Cidade;
import br.com.savetheday.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    Cidade findByNome( String nome );
    Cidade findByNomeAndEstado(String nome, Estado estado);
}
