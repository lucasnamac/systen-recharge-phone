package com.example.RecargasCelular.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.RecargasCelular.DAO.IClient;
import com.example.RecargasCelular.model.Client;

@RestController
public class ClientController {

    @Autowired
    private IClient dao;

    @PostMapping("/registerClient")
    public String RegisterClient(@RequestBody Client client) {
        dao.save(client);
        return "Cliente com CPF de número " + client.getCpf() + " adicionado com sucesso";
    }
}   
