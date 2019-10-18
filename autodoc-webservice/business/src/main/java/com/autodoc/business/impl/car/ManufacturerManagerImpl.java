package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.car.ManufacturerDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ManufacturerManagerImpl<D, T> extends AbstractGenericManager implements ManufacturerManager {
    private ManufacturerDaoImpl manufacturerDao;
    private Logger logger = Logger.getLogger(ManufacturerManagerImpl.class);


    public ManufacturerManagerImpl(ManufacturerDaoImpl manufacturerDao) {
        super(manufacturerDao);
        logger.debug("here");
        this.manufacturerDao = manufacturerDao;

    }


   /* @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturers = manufacturerDao.getAll();
        logger.info("trying to get maufacturers: " + manufacturers);
        return manufacturers;
    }*/

    @Override
    public ManufacturerDTO entityToDto(Object entity) {
        Manufacturer manufacturer = (Manufacturer) entity;
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO(manufacturer.getName());
        manufacturerDTO.setId(((Manufacturer) entity).getId());
        return manufacturerDTO;
    }

    @Override
    public ManufacturerDTO getByName(String name) {
        logger.debug("trying to get: " + name);
        if (name.isEmpty()) return null;
        return entityToDto(manufacturerDao.getByName(name));
    }


}
