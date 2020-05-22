package br.com.savetheday.controllers;

import br.com.savetheday.dtos.EnderecoDtoInput;
import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.services.EnderecoService;
import br.com.savetheday.services.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private OngService service;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ong salvar(@Valid @RequestBody OngDto dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<OngDtoModel> findAll() {
        return service.findAll();
    }

    @GetMapping( value = "/{id}")
    public OngDtoModel find(@PathVariable Integer id) {
        return service.find(id);
    }

    @PutMapping( value = "/{id}")
    public Ong update(@Valid @RequestBody OngDto ongDto, @PathVariable Integer id){
        return service.update(ongDto, id);
    }

    @DeleteMapping( value = "/{id}")
    public void delete(@Valid @PathVariable Integer id){
         service.delete(id);
    }

    @PostMapping(value = "/{id}/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvarEndereco(@Valid @RequestBody EnderecoDtoInput enderecoDtoInput, @PathVariable Integer id) {
        return enderecoService.save(enderecoDtoInput, id);
    }


    @PutMapping( value = "/{id}/endereco/{idEnd}")
    public Endereco updateEndereco(@Valid @RequestBody EnderecoDtoInput enderecoDtoInput,@PathVariable Integer id, @PathVariable Integer idEnd){
        return enderecoService.edit(enderecoDtoInput, id, idEnd);
    }

    @DeleteMapping( value = "/{id}/endereco/{idEnd}")
    public Boolean deleteEndereco(@PathVariable Integer id, @PathVariable Integer idEnd) {
        return enderecoService.delete(id, idEnd);
    }



}
