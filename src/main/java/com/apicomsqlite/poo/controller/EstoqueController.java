package com.apicomsqlite.poo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.apicomsqlite.poo.enity.Estoque;
import com.apicomsqlite.poo.service.EstoqueService;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @RequestMapping(value = "estoque", method = RequestMethod.GET)
    public String info() {
        return "Aplicacao ativa";
    }

    @RequestMapping(value = "createEstoque", method = RequestMethod.POST)
    public String createEstoque(@RequestBody Estoque estoque) {
        return estoqueService.createEstoque(estoque);
    }

    @RequestMapping(value = "readEstoque", method = RequestMethod.GET)
    public List<Estoque> readEstoque() {
        return estoqueService.readEstoque();
    }

    @RequestMapping(value = "updateEstoque", method = RequestMethod.PUT)
    public String updateEstoque(@RequestBody Estoque estoque) {
        return estoqueService.updateEstoque(estoque);
    }

    @RequestMapping(value = "deleteEstoque", method = RequestMethod.DELETE)
    public String deleteEstoque(@RequestBody Estoque estoque) {
        return estoqueService.deleteEstoque(estoque);
    }
}
