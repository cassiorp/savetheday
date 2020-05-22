package br.com.savetheday.services;

import br.com.savetheday.dtos.EnderecoDto;
import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.Endereco;

import java.util.List;

public interface EnderecoService {
    public Endereco save(EnderecoDto enderecoDto, Integer id);
    public List<EnderecoDtoModel> findAll();
    public Endereco edit(EnderecoDto enderecoDto, Integer idOng, Integer idEnd);
    public Boolean delete(Integer idOng, Integer idEnd);
}
