package br.com.savetheday.controllers;

import br.com.savetheday.dtos.CasoDto;
import br.com.savetheday.dtos.CasoDtoModel;
import br.com.savetheday.dtos.ValorDoadoDto;
import br.com.savetheday.entities.Caso;
import br.com.savetheday.services.CasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/caso")
public class CasoController {

    @Autowired
    private CasoService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Caso save(@Valid @RequestBody CasoDto dto){
        return service.save(dto);
    }

    @GetMapping
    @ResponseBody
    public List<CasoDtoModel> findAll(){
        return service.findAll();
    }

    @PutMapping( value = "/{id}" )
    @ResponseBody
    public Caso update(@Valid @RequestBody CasoDto dto, @PathVariable Integer id){
        return service.update(dto, id);
    }

    @DeleteMapping( value = "/{id}" )
    @ResponseBody
    public Boolean delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @PutMapping( value = "/doacao/{id}")
    @ResponseBody
    public String acrescenta(@Valid @RequestBody ValorDoadoDto dto, @PathVariable Integer id){
       return service.doacao(dto, id);
    }

    @GetMapping( value = "/{cidade}")
    @ResponseBody
    public List<CasoDtoModel> findAllCidade(@PathVariable String cidade){
        return service.filter(cidade);
    }

}
