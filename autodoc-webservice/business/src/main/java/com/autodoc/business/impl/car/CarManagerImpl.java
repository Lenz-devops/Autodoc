package com.autodoc.business.impl.car;

import com.autodoc.business.contract.car.CarManager;
import com.autodoc.business.contract.car.CarModelManager;
import com.autodoc.business.contract.person.client.ClientManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.person.client.Client;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

@Transactional
@Component
public class CarManagerImpl extends AbstractGenericManager implements CarManager {
    private Logger logger = Logger.getLogger(CarManagerImpl.class);
    private CarDaoImpl carDao;
    private CarModelManager carModelManager;
    private ClientManager clientManager;
    private ModelMapper mapper;

    public CarManagerImpl(ClientManager clientManager, CarDaoImpl dao, CarModelManager carModelManager) {
        super(dao);
        this.carModelManager = carModelManager;
        this.clientManager = clientManager;
        this.carDao = dao;
        this.mapper = new ModelMapper();
        logger.info("creating manager");
    }


    @Override
    public CarDTO getByRegistration(String registration) {
        logger.info("reg: " + registration);
        CarDTO carDTO = entityToDto(carDao.getCarByRegistration(registration));
        return carDTO;
    }


    @Override
    public CarDTO updateClient(int carId, int clientId) throws Exception {
        CarDTO carDto = (CarDTO) carDao.getById(carId);
        Client client = (Client) clientManager.getById(clientId);
        if (client == null) throw  new NotFoundException("client not found");
        /*if (car == null) return "no car found";
        car.setClient(client);
        return car;*/
        return null;
    }


    @Override
    public CarDTO entityToDto(Object car1) {
        logger.info("converting into dto");

        CarDTO carDTO = mapper.map(car1, CarDTO.class);
        Car car = (Car) car1;

        carDTO.setCarModelId(car.getCarModel().getId());
        carDTO.setClientId(car.getClient().getId());
        return carDTO;
    }

    @Override
    public Car dtoToEntity(Object entity) throws Exception {
        logger.info("converting into entity");
        resetException();
        CarDTO dto = (CarDTO) entity;
        System.out.println("dto: " + dto);
        Car car = mapper.map(dto, Car.class);
        Client client = (Client) clientManager.getById(dto.getClientId());
        if(client ==null) {
            throw new Exception("invalid ccalient id: "+dto.getClientId());
            //exception += "\n invalid clientId: "+dto.getClientId();

        }
        CarModel carModel = (CarModel) carModelManager.getById(dto.getCarModelId());
        if(carModel ==null){
            exception +="\n invalid carModelId: "+dto.getCarModelId();
        }

        System.out.println("client: " + client);
        System.out.println("carModel: " + carModel);
        car.setClient(client);
        car.setCarModel(carModel);
        //car.setRegistration(dto.getRegistration());
        logger.info("car: " + car);
        return car;
    }




}
