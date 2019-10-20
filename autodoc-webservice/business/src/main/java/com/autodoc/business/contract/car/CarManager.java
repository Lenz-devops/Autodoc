package com.autodoc.business.contract.car;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.car.CarDTO;
import org.springframework.stereotype.Service;

@Service
public interface CarManager extends IGenericManager {


    CarDTO getByRegistration(String registration);


    String updateClient(int carId, int clientId);

}
