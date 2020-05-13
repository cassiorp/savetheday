package br.com.savetheday.controllers;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.services.ContaService;
import br.com.savetheday.services.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private OngService service;

    @Autowired
    ContaService contaService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Ong salvar(@Valid @RequestBody OngDto dto) {
        return service.save(dto);
    }

    @GetMapping
    @ResponseBody
    public List<OngDtoModel> findAll() {
        return service.findAll();
    }

    @GetMapping( value = "/{id}")
    @ResponseBody
    public OngDtoModel find(@PathVariable Integer id) {
        return service.find(id);
    }

//    @PutMapping( value = "/{id}")
//    @ResponseBody
//    public Ong edit(@Valid @RequestBody OngDtoEdit ongDtoEdit, @PathVariable Integer id){
//        return service.edit(ongDtoEdit, id);
//    }

    @PostMapping( value = "/{id}/conta")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@Valid @RequestBody ContaDto conta, @PathVariable Integer id) {
        return contaService.save(conta, id);
    }

}