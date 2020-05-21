package br.com.savetheday.services;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.dtos.ContaDtoModel;
import br.com.savetheday.entities.Conta;

import java.util.List;

public interface ContaService {
    public Conta save(ContaDto dto);
    public List<ContaDtoModel> findAll();
    public Boolean delete(Integer id);
    public Conta update(ContaDto dto, Integer id);
}
