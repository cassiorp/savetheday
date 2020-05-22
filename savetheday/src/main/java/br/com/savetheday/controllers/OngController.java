package br.com.savetheday.controllers;

import br.com.savetheday.dtos.ContaDto;
import br.com.savetheday.dtos.EnderecoDto;
import br.com.savetheday.dtos.OngDto;
import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Conta;
import br.com.savetheday.entities.Endereco;
import br.com.savetheday.entities.Ong;
import br.com.savetheday.services.ContaService;
import br.com.savetheday.services.EnderecoService;
import br.com.savetheday.services.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/ong")
public class OngController {

    @Autowired
    private OngService service;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContaService contaService;

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
    public Endereco salvarEndereco(@Valid @RequestBody EnderecoDto enderecoDto, @PathVariable Integer id) {
        return enderecoService.save(enderecoDto, id);
    }

    @PutMapping( value = "/{id}/endereco/{idEnd}")
    public Endereco updateEndereco(@Valid @RequestBody EnderecoDto enderecoDto, @PathVariable Integer id, @PathVariable Integer idEnd){
        return enderecoService.edit(enderecoDto, id, idEnd);
    }

    @DeleteMapping( value = "/{id}/endereco/{idEnd}")
    public Boolean deleteEndereco(@PathVariable Integer id, @PathVariable Integer idEnd) {
        return enderecoService.delete(id, idEnd);
    }

    @PostMapping(value = "/{id}/conta")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta saveConta(@Valid @RequestBody ContaDto contaDto, @PathVariable Integer id) {
        return contaService.save(contaDto, id);
    }

    @PutMapping( value = "/{id}/conta/{idConta}")
    public Conta updateConta(@Valid @RequestBody ContaDto contaDto, @PathVariable Integer id, @PathVariable Integer idConta){
        return contaService.update(contaDto, id, idConta);
    }

    @DeleteMapping( value = "/{id}/conta/{idConta}")
    public Boolean delete(@Valid @PathVariable Integer id, @PathVariable Integer idConta){
        return contaService.delete(id, idConta);
    }
}
