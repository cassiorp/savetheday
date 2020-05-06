package br.com.savetheday.controllers;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.dtos.EnderecoDtoModel;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@Valid @RequestBody EnderecoDtoInput enderecoDtoInput) {
        return service.save(enderecoDtoInput);
    }

    @GetMapping
    @ResponseBody
    public List<EnderecoDtoModel> findAll() {
        return service.findAll();
    }

    @PutMapping( value = "/{id}")
    @ResponseBody
    public Endereco edit(@Valid @RequestBody EnderecoDtoInput enderecoDtoInput, @PathVariable Integer id){
        return service.edit(enderecoDtoInput, id);
    }

    @DeleteMapping( value = "/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.ifExists(id)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }





}
