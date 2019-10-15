package com.autodoc.business.impl.person.client;

import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class ClientManagerImpl implements ClientManager {
    private ClientDaoImpl clientDao;
    private Logger logger = Logger.getLogger(ClientManagerImpl.class);


    public ClientManagerImpl(ClientDaoImpl clientDao) {
        this.clientDao = clientDao;

    }


    @Override
    public String save(Client client) {
        clientDao.create(client);
        return "client added";
    }

    @Override
    public List<Client> getAll() {
        logger.debug("trying to get clients");
        return clientDao.findAll();
    }

    @Override
    public String update(Client client) {
        try {
            clientDao.update(client);
            return "client updated";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(int anyInt) {
        return null;
    }
}
