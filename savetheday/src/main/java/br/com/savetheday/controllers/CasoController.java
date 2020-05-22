package br.com.savetheday.controllers;

import br.com.savetheday.dtos.CasoDto;
import br.com.savetheday.dtos.CasoDtoModel;
import br.com.savetheday.dtos.ValorDoadoDto;
import br.com.savetheday.entities.Caso;
import br.com.savetheday.services.CasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/caso")
public class CasoController {

    @Autowired
    private CasoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Caso save(@Valid @RequestBody CasoDto dto){
        return service.save(dto);
    }

    @GetMapping
    public List<CasoDtoModel> findAll(){
        return service.findAll();
    }

    @GetMapping( value = "/{id}")
    public CasoDtoModel find(@PathVariable Integer id){
        return service.find(id);
    }

    @PutMapping( value = "/{id}" )
    public Caso update(@Valid @RequestBody CasoDto dto, @PathVariable Integer id){
        return service.update(dto, id);
    }

    @DeleteMapping( value = "/{id}" )
    public Boolean delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @PutMapping( value = "/doacao/{id}")
    public String doacao(@Valid @RequestBody ValorDoadoDto dto, @PathVariable Integer id){
       return service.doacao(dto, id);
    }

    @GetMapping( value = "/cidade/{cidade}")
    public List<CasoDtoModel> findAllCidade(@PathVariable String cidade){
        return service.filter(cidade);
    }

}
