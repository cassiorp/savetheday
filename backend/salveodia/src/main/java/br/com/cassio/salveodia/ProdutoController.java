package br.com.cassio.salveodia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( value = "api")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @PostMapping(value = "/new" )
    @ResponseBody
    public Produto salvar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping(value = "/get" )
    @ResponseBody
    public List<Produto> buscar() {
        return (List<Produto>) repository.findAll();
    }
}
