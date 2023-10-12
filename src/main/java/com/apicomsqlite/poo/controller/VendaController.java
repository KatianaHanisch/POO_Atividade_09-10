package com.apicomsqlite.poo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.apicomsqlite.poo.enity.Venda;
import com.apicomsqlite.poo.service.VendaService;

@RestController
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @RequestMapping(value = "venda", method = RequestMethod.GET)
    public String info() {
        return "Aplicacao ativa";
    }

    @RequestMapping(value = "createVenda", method = RequestMethod.POST)
    public String createVenda(@RequestBody Venda venda) {
        return vendaService.createVenda(venda);
    }

    @RequestMapping(value = "readVendas", method = RequestMethod.GET)
    public List<Venda> readVendas() {
        return vendaService.readVendas();
    }

    @RequestMapping(value = "updateVenda", method = RequestMethod.PUT)
    public String updateVenda(@RequestBody Venda venda) {
        return vendaService.updateVenda(venda);
    }

    @RequestMapping(value = "deleteVenda", method = RequestMethod.DELETE)
    public String deleteVenda(@RequestBody Venda venda) {
        return vendaService.deleteVenda(venda);
    }
}
