package br.com.savetheday.services;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.Endereco;

import java.util.List;

public interface EnderecoService {
    public Endereco save(EnderecoDtoInput enderecoDtoInput);
    public List<EnderecoDtoModel> findAll();
    public Endereco edit(EnderecoDtoInput enderecoDtoInput, Integer id);
    public Boolean delete(Integer id);
}
