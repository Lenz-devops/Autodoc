package com.autodoc.controllers.impl.car;


import com.autodoc.business.contract.car.CarManager;
import com.autodoc.controllers.contract.car.CarController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.car.CarDTO;
import com.autodoc.model.models.car.Car;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarControllerImpl extends GlobalControllerImpl<Car, CarDTO> implements CarController {
    private Logger logger = Logger.getLogger(CarControllerImpl.class);
    private CarManager carManager;

    private GsonConverter converter;

    public CarControllerImpl(CarManager carManager) {
        super(carManager);
        if (converter == null) converter = new GsonConverter();
        this.carManager = carManager;
    }


    @Override
    @GetMapping(value = "/getByRegistration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCarByRegistration(@RequestBody String registration) {
        CarDTO car = carManager.getByRegistration(registration);
        String response = converter.convertObjectIntoGsonObject(car);

        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity getByClient(String clientLastName, String clientFirstName) {
        return null;
    }


    @Override
    @PutMapping(value = "/updateClient/{carId}/{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCarClient(@PathVariable Integer carId, @PathVariable Integer clientId) {
        logger.debug("car id: " + carId + " / client id: " + clientId);
        String response = carManager.updateClient(carId, clientId);
        if (response.equals("car updated")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


}
