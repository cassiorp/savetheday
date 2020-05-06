package br.com.savetheday.repositories;

import br.com.savetheday.entities.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, Integer> {
    Ong findByCnpj( String cnpj );
    Ong findByEmail( String email );
}
