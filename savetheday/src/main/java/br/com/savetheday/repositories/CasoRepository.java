package br.com.savetheday.repositories;

import br.com.savetheday.entities.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CasoRepository extends JpaRepository<Caso, Integer> {
    List<Caso> findByOng_Endereco_Cidade_NomeContaining( String nome );
}
