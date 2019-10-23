package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ManufacturerManagerImpl<D, T> extends AbstractGenericManager implements ManufacturerManager {
    private ManufacturerDaoImpl manufacturerDao;
    private Logger logger = Logger.getLogger(ManufacturerManagerImpl.class);
    private ModelMapper mapper;

    public ManufacturerManagerImpl(ManufacturerDaoImpl manufacturerDao) {
        super(manufacturerDao);
        this.mapper = new ModelMapper();
        logger.debug("here");
        this.manufacturerDao = manufacturerDao;

    }


    @Override
    public ManufacturerDTO entityToDto(Object entity) {
        Manufacturer manufacturer = (Manufacturer) entity;
       /* ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setName(manufacturer.getName());
        manufacturerDTO.setId(((Manufacturer) entity).getId());
        return manufacturerDTO;*/
        //TODO MODELMAPPING
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }

    @Override
    public ManufacturerDTO getByName(String name) {
        logger.debug("trying to get: " + name);
        if (name.isEmpty()) return null;
        return entityToDto(manufacturerDao.getByName(name));
    }


}
