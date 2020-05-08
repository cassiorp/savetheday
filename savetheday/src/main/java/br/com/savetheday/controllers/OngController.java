package br.com.savetheday.controllers;

import br.com.savetheday.dtos.OngDtoModel;
import br.com.savetheday.entities.Ong;
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

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Ong salvar(@Valid @RequestBody Ong ong) {
        return service.save(ong);
    }

    @GetMapping
    @ResponseBody
    public List<OngDtoModel> findAll() {
        return service.findAll();
    }

    @GetMapping( value = "/{id}")
    @ResponseBody
    public Ong findById(@PathVariable Integer id) {
        return service.findById(id);
    }

}
