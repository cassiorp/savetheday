package br.com.savetheday.controllers;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@Valid @RequestBody ContaDto contaDto) {
        return service.save(contaDto);
    }

    @PutMapping( value = "/{id}")
    @ResponseBody
    public Conta update(@Valid @RequestBody ContaDto contaDto, @PathVariable Integer id){
        return service.update(contaDto, id);
    }

    @DeleteMapping( value = "/{id}")
    @ResponseBody
    public Boolean delete(@Valid @PathVariable Integer id){
         return service.delete(id);
    }

}
