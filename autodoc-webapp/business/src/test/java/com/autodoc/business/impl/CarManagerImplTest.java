package com.autodoc.business.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.CarModelManager;
import com.autodoc.business.contract.ClientManager;
import com.autodoc.business.contract.ManufacturerManager;
import com.autodoc.contract.BillService;
import com.autodoc.contract.CarService;
import com.autodoc.contract.EnumService;
import com.autodoc.impl.CarModelServiceImpl;
import com.autodoc.impl.CarServiceImpl;
import com.autodoc.impl.ClientServiceImpl;
import com.autodoc.impl.ManufacturerServiceImpl;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarManagerImplTest {

    // private static final String BASE_URL = "http://localhost:8087/autodoc/";
    // private final ObjectMapper objectMapper = new ObjectMapper();
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMTU9MTyIsImV4cCI6MTU3NDk2ODA1MCwiaWF0IjoxNTc0OTUwMDUwfQ.BfKxkfEVLatJRdOfVph7XqJHnCBWPGFKyaq2exJNUYFMr9lU0-EgQIQ_vecx8oqQIV1GEJ3vP8mSjUhu1vfrdA";
    // String url = "http://localhost:8087/autodoc/employees";
    private static final Logger LOGGER = Logger.getLogger(CarManagerImplTest.class);
    private CarManager carManager;
    //@Inject
    private CarService service;
    @Inject
    private ClientManager clientManager;
    @Inject
    private CarModelManager carModelManager;
    @Inject
    private EnumService enumService;
    private ManufacturerManager manufacturerManager;
    @Inject
    private BillService billService;

    @BeforeEach
    void init() {
        service = new CarServiceImpl();
        clientManager = new ClientManagerImpl(new ClientServiceImpl(), enumService);
        manufacturerManager = new ManufacturerManagerImpl(new ManufacturerServiceImpl());
        carModelManager = new CarModelManagerImpl(new CarModelServiceImpl(), manufacturerManager);
        carManager = new CarManagerImpl(service, carModelManager, clientManager, billService);
    }

    @Test
    void getByRegistration() throws Exception {
        Car car = new Car();
        //when(service.getByRegistration(anyString(), anyString())).thenReturn(car);

        String registration = "05D154875";
        LOGGER.info("mgr: " + carManager);
        assertNotNull(carManager.getByRegistration(token, registration));
    }


    @Test
    void getById() throws Exception {
        int id = 2;

        assertNotNull(carManager.getById(token, id));
    }


}