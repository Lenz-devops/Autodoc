package com.autodoc.business.impl;


import com.autodoc.business.contract.ProviderManager;
import com.autodoc.contract.ProviderService;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.autodoc.model.dtos.person.provider.ProviderForm;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.Logger;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ProviderManagerImpl extends GlobalManagerImpl<Provider, ProviderDTO> implements ProviderManager {

    private static final Logger LOGGER = Logger.getLogger(ProviderManagerImpl.class);
    public Provider provider;
    private ProviderService service;

    public ProviderManagerImpl(ProviderService service) {
        super(service);
        this.service = service;
    }

    public Provider dtoToEntity(String token, Object obj) {

        ProviderDTO dto = (ProviderDTO) obj;
        LOGGER.info("dto: " + dto);
        Provider provider = new Provider();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        provider.setId(id);
        provider.setFirstName(dto.getFirstName());
        provider.setLastName(dto.getLastName());
        provider.setPhoneNumber1(dto.getPhoneNumber1());
        provider.setEmail(dto.getEmail1());
        provider.setWebsite(dto.getWebsite());
        provider.setCompany(dto.getCompany());
        LOGGER.info("provider transferred: " + provider);

        return provider;
    }

    public ProviderDTO formToDto(Object obj) {
        LOGGER.info("stuff to update: " + obj);
        ProviderForm dto = (ProviderForm) obj;
        LOGGER.info("dto: " + dto);
        ProviderDTO provider = new ProviderDTO();
        if (dto.getId() != 0) provider.setId(dto.getId());
        provider.setCompany(dto.getCompany());
        provider.setFirstName(dto.getFirstName());
        provider.setLastName(dto.getLastName());
        provider.setEmail1(dto.getEmail());
        provider.setPhoneNumber1(dto.getPhoneNumber());
        LOGGER.info("provider transferred: " + provider);
        return provider;
    }

/*    List<Provider> convertList(String token, List<ProviderDTO> list) {
        LOGGER.info("converting list: "+list);
        List<Provider> newList = new ArrayList<>();
        for (ProviderDTO obj : list) {
            newList.add(dtoToEntity(token, obj));
        }
        LOGGER.info("new list: "+newList);
        return newList;
    }*/

}