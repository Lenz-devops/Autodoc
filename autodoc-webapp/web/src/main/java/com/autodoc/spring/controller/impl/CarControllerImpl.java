package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.CarManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.RegistrationForm;
import com.autodoc.model.models.car.Car;
import com.autodoc.spring.controller.contract.CarController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@ControllerAdvice
@RequestMapping("/cars")
public class CarControllerImpl extends GlobalController implements CarController {
    private static Logger LOGGER = Logger.getLogger(CarControllerImpl.class);
    @Inject
    private CarManager carManager;

    public CarControllerImpl(LibraryHelper helper) {
        super(helper);
    }

    @PostMapping("/searchCar")
    public ModelAndView searchCar(@Valid RegistrationForm registrationForm, BindingResult bindingResult) throws Exception {
        System.out.println("getting herer: " + registrationForm);
        LOGGER.info("retrieving searchCar");
        String registration = registrationForm.getRegistration().toUpperCase();
        ModelAndView mv = checkAndAddConnectedDetails("operations");

        if (bindingResult.hasErrors()) {
            return mv;
        }


        Car car = carManager.getByRegistration(helper.getConnectedToken(), registration);
        LOGGER.info("car found: " + car);
        if (car == null) {
            //mv = checkAndAddEmployeeDetails("operations");
            mv.addObject("message", "no car found");
            return mv;
        }
        System.out.println("car found: " + registration);
        System.out.println("owner: "+car.getClient().getLastName());
        mv.addObject("car", car);
        mv.addObject("client", car.getClient());
        mv.addObject("model", car.getModel());
        return mv;


    }

   /* private boolean validateRegistration(String registration) {
        if (registration == null) return false;
        int length = registration.length();
        System.out.println("length: " + length);
        return length >= 5 && length <= 10;
    }*/


}

