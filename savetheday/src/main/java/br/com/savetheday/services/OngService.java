package br.com.savetheday.services;

import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Ong;

import java.util.List;

public interface OngService {

    public Ong save(OngDto dto);
    public OngDtoModel find(Integer id);
    public List<OngDtoModel> findAll();
    public void delete(Integer id);
    public Ong update(OngDto dto, Integer id);
}
