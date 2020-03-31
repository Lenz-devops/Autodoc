package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ManufacturerManagerImpl<D, T> extends AbstractGenericManager implements ManufacturerManager {
    private ManufacturerDao manufacturerDao;
    private static final Logger LOGGER = Logger.getLogger(ManufacturerManagerImpl.class);
    private ModelMapper mapper;
    private Link link = new Link("http://localhost:8087/autodoc/manufacturers/");

    public ManufacturerManagerImpl(ManufacturerDao manufacturerDao) {
        //super(manufacturerDao);
        this.mapper = new ModelMapper();
        this.manufacturerDao = manufacturerDao;

    }


    @Override
    public ManufacturerDTO entityToDto(Object entity) {
        ManufacturerDTO dto = mapper.map(entity, ManufacturerDTO.class);

        //.add(link);
        Manufacturer manufacturer = (Manufacturer) entity;
        dto.setId(manufacturer.getId());
        LOGGER.info("converted into dto");
        return dto;
    }

    @Override
    public Manufacturer dtoToEntity(Object entity) throws InvalidDtoException {
        LOGGER.info("converted into ");
        ManufacturerDTO dto = (ManufacturerDTO) entity;
        Manufacturer manufacturer = mapper.map(entity, Manufacturer.class);
        checkIfDuplicate(dto);
        return manufacturer;
    }

    @Override
    public ManufacturerDTO getByName(String name) {
        LOGGER.debug("trying to get: " + name);
        LOGGER.info("trying to get " + name);
        name = name.toUpperCase();
        LOGGER.info("name now: " + name);
        if (name.isEmpty()) return null;
        return entityToDto(manufacturerDao.getByName(name));
    }


    @Override
    public void checkIfDuplicate(Object dtoToCheck) throws InvalidDtoException {
        ManufacturerDTO dto = (ManufacturerDTO) dtoToCheck;
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidDtoException("there should be a name");
        }
        if (manufacturerDao.getByName(dto.getName()) != null) {
            throw new InvalidDtoException("Manufacturer already exist with that name");
        }
        LOGGER.info("all good");
    }

}
