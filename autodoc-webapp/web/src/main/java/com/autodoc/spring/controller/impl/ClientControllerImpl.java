package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.ClientManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.person.client.ClientDTO;
import com.autodoc.model.dtos.person.client.ClientForm;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.spring.controller.contract.ClientController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/clients")
public class ClientControllerImpl extends GlobalController<Client, ClientDTO> implements ClientController {

    private static Logger LOGGER = Logger.getLogger(ClientControllerImpl.class);
    private static final String KEY_WORD = "clients";
    // ClientManager manager;

    public ClientControllerImpl(LibraryHelper helper, ClientManager manager) {
        super(helper);
        this.manager = manager;
    }


    @Override
    String getKeyWord() {
        return KEY_WORD;
    }


    @GetMapping("")
    public ModelAndView clients() throws Exception {
        return getList();

    }



  /*  List<Client> getAll() throws Exception {
        List<Client> list = (List<Client>) manager.getAll(helper.getConnectedToken());
        return list;
    }*/

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView clientById(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to get " + KEY_WORD + " with id " + id);
        ModelAndView mv = checkAndAddConnectedDetails(KEY_WORD + "/" + KEY_WORD + "_details");
        LOGGER.info("client is null");
        Client client = (Client) manager.getById(helper.getConnectedToken(), id);
        mv.addObject("form", client);
        mv.addObject("showForm", 1);
        mv.addObject("client", client);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid ClientForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + form.getId());
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_details");
        mv.addObject("form", new ClientForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            Client client = (Client) manager.getById(helper.getConnectedToken(), form.getId());
            mv.addObject("client", client);
            mv.addObject("form", form);
            mv.addObject("showForm", 0);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("client retrieved: " + form);
        manager.update(helper.getConnectedToken(), form);
        return new ModelAndView("redirect:" + "/clients/" + form.getId());
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return clients();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_new");
        mv.addObject("clientForm", new ClientForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid ClientForm form, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("clients/clients_new");
        mv.addObject("form", new ClientForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("form", form);
            mv.addObject("showForm", 1);
            return mv;
        }
        String feedback = manager.add(helper.getConnectedToken(), form);
        try {
            int id = Integer.parseInt(feedback);
            return new ModelAndView("redirect:" + "/" + KEY_WORD + "/" + id);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            mv.addObject("error", feedback);
        }
        return mv;
    }


}

