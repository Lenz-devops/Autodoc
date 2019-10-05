package com.autodoc.dao.impl;

import com.autodoc.dao.contract.ManufacturerDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ManufacturerDaoImpl<T extends Serializable> extends AbstractHibernateDao implements ManufacturerDao {
    private Logger logger = Logger.getLogger(ManufacturerDaoImpl.class);


    public ManufacturerDaoImpl() {
        System.out.println("creating manuf dao");
        this.setClazz(Car.class);
    }


}
