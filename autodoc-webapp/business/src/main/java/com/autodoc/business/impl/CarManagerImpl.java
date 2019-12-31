package com.autodoc.business.impl;


import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.contract.CarService;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class CarManagerImpl extends GlobalManagerImpl<Car, CarDTO> implements CarManager {

    private static final Logger LOGGER = Logger.getLogger(CarManagerImpl.class);


    private CarService service;

    //@Inject
    private CarModelManager carModelManager;
    // @Inject
    private ClientManager clientManager;

    public CarManagerImpl(CarService service, CarModelManager carModelManager, ClientManager clientManager) {
        super(service);
        this.service = service;
        this.carModelManager = carModelManager;
        this.clientManager = clientManager;
        LOGGER.info("created stuff" + clientManager);
    }

    @Override
    public Car getByRegistration(String token, String registration) throws Exception {
        LOGGER.info("trying to get car by registration");
        LOGGER.info(service);
        CarDTO dto = service.getByRegistration(token, registration);
        if (dto == null) return null;
        Car car = dtoToEntity(token, dto);
        LOGGER.info("yoyho");
        if (car == null) {
            LOGGER.info("car is null");
            return null;
        }
        return car;
    }

    public Car dtoToEntity(String token, Object obj) throws Exception {
        LOGGER.info("convert into car");
        CarDTO dto = (CarDTO) obj;
        Car car = new Car();
        car.setId(dto.getId());
        car.setRegistration(dto.getRegistration());
        car.setClient(setClient(token, dto.getClientId()));
        car.setModel(setModel(token, dto.getCarModelId()));
        LOGGER.info("car: " + car);
        return car;
    }

    private CarModel setModel(String token, int modelId) throws Exception {
        LOGGER.info("modelId: " + modelId);
        if (modelId <= 0) return null;
        CarModel model = (CarModel) carModelManager.getById(token, modelId);
        LOGGER.info("model found: " + model);
        return model;
    }

    private Client setClient(String token, int clientId) throws Exception {
        if (clientId <= 0) return null;
        LOGGER.info("clientId: " + clientId);
        LOGGER.info("clientManager " + clientManager);
        Client client = (Client) clientManager.getById(token, clientId);
        LOGGER.info("client found: " + client);
        return client;
    }


}
