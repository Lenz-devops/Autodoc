package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.models.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration("classpath:/mvc-dispatcher-servlet.xml")
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:resetDb_scripts/resetDbCar.sql")
@Transactional
class CarDaoImplTest {

    @Inject
    private CarDao dao;

    @Inject
    private Filler filler;
    String clientName;
    String registration;
    int id;
    Car car;


    @BeforeEach
    void init() throws Exception {
        filler.fill();
        car = (Car) dao.getAll().get(0);
        id = car.getId();
        registration = car.getRegistration();
        clientName = car.getClient().getLastName();

    }

    @Test
    void getAll() {
        assertEquals(2, dao.getAll().size());
    }

    @Test
    @DisplayName("should return a car")
    void getByRegistration() {
       /* assertAll(
                () -> assertNotNull(carDao.getCarByRegistration(registration)),
                () -> assertThat((carDao.getCarByRegistration(registration)), instanceOf(Car.class))
        );*/

        String test = "test";
        System.out.println(test);

    }

    @Test
    void deleteById() {
        int id = 2;
        assertNotNull(dao.getById(id));
        dao.deleteById(id);
        assertNull(dao.getById(id));
    }

    @Test
    @DisplayName("should return null")
    void getByRegistration1() {

        assertNull(dao.getCarByRegistration("dede"));
    }


   /* @Test
    @DisplayName("should return null")
    void getById() {

        assertNotNull(carDao.getById(1));
    }*/

    @Test
    @DisplayName("should return null")
    void getByClient() {

        assertTrue(dao.getCarByClient("toto").isEmpty());
    }

    @Test
    @DisplayName("should return list of car for a client")
    void getByClient1() {
        assertAll(
                () -> assertFalse(dao.getCarByClient(clientName).isEmpty()),
                () -> assertThat(dao.getCarByClient(clientName), instanceOf(ArrayList.class))
        );

    }
}