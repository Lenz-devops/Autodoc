package com.autodoc.controllers.impl;


import com.autodoc.business.contract.ClientManager;
import com.autodoc.controllers.contract.ClientController;
import com.autodoc.model.models.person.client.Client;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientControllerImpl implements ClientController {
    private Logger logger = Logger.getLogger(ClientControllerImpl.class);
    private ClientManager clientManager;

    public ClientControllerImpl(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @GetMapping(value = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAll() {
        System.out.println("Called gettho");

        List<Client> list = clientManager.getAll();
        System.out.println("Loaded |" + list + "|");
        String response = convertObjectIntoGsonObject(list);
        System.out.println("Returning |" + response + "|");

        return response;
    }

    @Override
    public Client getClientByName(String name) {
        return null;
    }

    @Override
    public String getClientById(int id) {
        return null;
    }

    @Override
    public String addClient(Client client) {
        return clientManager.save(client);
    }

    @Override
    public String updateClient() {
        return null;
    }

    @Override
    public String deleteClient(int clientId) {
        return null;
    }


    public String convertObjectIntoGsonObject(Object list) {
        return new Gson().toJson(list);
    }


}
