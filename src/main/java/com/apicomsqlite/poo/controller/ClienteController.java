package com.apicomsqlite.poo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.apicomsqlite.poo.enity.Cliente;
import com.apicomsqlite.poo.service.ClienteService;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "pessoa", method = RequestMethod.GET)
    public String info() {
        return "Aplicacao ativa";
    }

    @RequestMapping(value = "createCliente", method = RequestMethod.POST)
    public String createCliente(@RequestBody Cliente pessoa) {
        return clienteService.createCliente(pessoa);
    }

    @RequestMapping(value = "readCliente", method = RequestMethod.GET)
    public List<Cliente> readCliente() {
        return clienteService.readCliente();
    }

    @RequestMapping(value = "updateCliente", method = RequestMethod.PUT)
    public String updateCliente(@RequestBody Cliente pessoa) {
        return clienteService.updateCliente(pessoa);
    }

    @RequestMapping(value = "deleteCliente", method = RequestMethod.DELETE)
    public String deleteCliente(@RequestBody Cliente pessoa) {
        return clienteService.deleteCliente(pessoa);
    }
}
