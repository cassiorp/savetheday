package br.com.savetheday.services;

import br.com.savetheday.dtos.CasoDto;
import br.com.savetheday.dtos.CasoDtoModel;
import br.com.savetheday.dtos.ValorDoadoDto;
import br.com.savetheday.entities.Caso;

import java.util.List;

public interface CasoService {
    public Caso save(CasoDto dto);
    public List<CasoDtoModel> findAll();
    public Caso update(CasoDto dto, Integer id);
    public Boolean delete(Integer id);
    public String doacao(ValorDoadoDto dto, Integer id);
    public List<CasoDtoModel> filter(String nome);
    public CasoDtoModel find(Integer id);
    public Caso findById(Integer id);

}
