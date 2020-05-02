package br.com.savetheday.controllers;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    @ResponseBody
    public Endereco salvar(@Valid @RequestBody EnderecoDtoInput enderecoDtoInput) {
        return service.salvar(enderecoDtoInput);
    }
}
