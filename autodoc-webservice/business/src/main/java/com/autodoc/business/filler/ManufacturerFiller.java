package com.autodoc.business.filler;

import com.autodoc.dao.impl.car.CarDaoImpl;
import com.autodoc.dao.impl.car.CarModelDaoImpl;
import com.autodoc.dao.impl.car.ManufacturerDaoImpl;
import com.autodoc.dao.impl.person.client.ClientDaoImpl;
import com.autodoc.dao.impl.person.employee.EmployeeDaoImpl;
import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import com.autodoc.model.enums.Role;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.car.Manufacturer;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class ManufacturerFiller {

    private Logger logger = Logger.getLogger(ManufacturerFiller.class);

    private ManufacturerDaoImpl manufacturerDao;
    private CarModelDaoImpl carModelDao;
    private EmployeeDaoImpl<Employee> employeeDao;
    private ClientDaoImpl<Client> clientDao;
    private CarDaoImpl<Car> carDao;

    public ManufacturerFiller(ManufacturerDaoImpl manufacturerDao, CarModelDaoImpl carModelDao, EmployeeDaoImpl<Employee> employeeDao, ClientDaoImpl<Client> clientDao, CarDaoImpl<Car> carDao) {
        this.manufacturerDao = manufacturerDao;
        this.carModelDao = carModelDao;
        this.employeeDao = employeeDao;
        this.clientDao = clientDao;
        this.carDao = carDao;
    }

    public void fill() {
        logger.debug("getting here");
        fillEmployee();
        fillManufacturer();
        fillCarModel();
        fillClient();
        fillCar();
    }

    private void fillManufacturer() {
        logger.debug("filling manufacturer");
        String[] list = {"AUDI", "BMW", "RENAULT", "OPEL", "NISSAN", "TOYOTA"};
        for (int i = 0; i < list.length; i++) {
            manufacturerDao.create(new Manufacturer(list[i]));
        }
    }


    private void fillCarModel() {
        logger.debug("filling car model");
        Manufacturer man = manufacturerDao.getByName("NISSAN");
        carModelDao.create(new CarModel(man, "QASHQAI", "VISIA DCI", GearBox.AUTOMATIC, "1528", FuelType.DIESEL));
        man = manufacturerDao.getByName("RENAULT");
        carModelDao.create(new CarModel(man, "CLIO", "BEBOP", GearBox.MANUAL, "1528", FuelType.PETROL));
        man = manufacturerDao.getByName("TOYOTA");
        carModelDao.create(new CarModel(man, "AURIS", "T SPIRIT D4D", GearBox.MANUAL, "1998", FuelType.HYBRID));
    }

    private void fillClient() {
        logger.debug("filling client");
        Client client = new Client("LOKII", "MOLO", "03938937837");
        clientDao.create(client);
    }

    private void fillCar() {
        logger.debug("filling car");
        CarModel carModel = carModelDao.findByName("AURIS");
        Client client = (Client) clientDao.findAll().get(0);
        Car car = new Car("05D154875", carModel, client);
        carDao.create(car);
    }

    private void fillEmployee() {
        logger.debug("filling employee");
        List<Role> roleList = new ArrayList<>();
        String login = "LMOLO";
        Employee employee = new Employee("LOKII", "MOLO", "03938937837", roleList, new Date(), login, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
        employeeDao.create(employee);
       /* if (employeeDao.getByLogin(login)==null) {
            logger.debug("employee created");
        }*/
    }


}
