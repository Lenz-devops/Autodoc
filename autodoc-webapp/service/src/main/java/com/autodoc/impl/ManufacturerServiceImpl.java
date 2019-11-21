package com.autodoc.impl;

import com.autodoc.contract.ManufacturerService;
import com.autodoc.model.Manufacturer;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class ManufacturerServiceImpl extends GlobalServiceImpl<Manufacturer> implements ManufacturerService {
    private static Logger LOGGER = Logger.getLogger(ManufacturerServiceImpl.class);

    Class getObjectClass() {
        return Manufacturer.class;
    }


}