package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.bill.BillDao;
import com.autodoc.dao.contract.car.CarDao;
import com.autodoc.dao.contract.person.client.ClientDao;
import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.contract.tasks.TaskDao;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.enums.Status;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.car.Car;
import com.autodoc.model.models.employee.Employee;
import com.autodoc.model.models.person.client.Client;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class BillManagerImpl extends AbstractGenericManager implements BillManager {
    private static final Logger LOGGER = Logger.getLogger(BillManagerImpl.class);
    private BillDao billDao;
    private ModelMapper mapper;
    private  CarDao carDao;
    private  ClientDao clientDao;
    private EmployeeDao employeeDao;
    private  TaskDao taskDao;

    public BillManagerImpl(BillDao billDao, CarDao carDao, ClientDao clientDao,  EmployeeDao employeeDao, TaskDao taskDao) {
        super(billDao);
        this.mapper = new ModelMapper();
        this.billDao = billDao;
        this.carDao = carDao;
        this.clientDao = clientDao;
        this.employeeDao = employeeDao;
        this.taskDao = taskDao;
    }


    @Override
    public BillDTO entityToDto(Object entity) {
        LOGGER.info("convert to dto");
        //BillDTO dto = mapper.map(entity, BillDTO.class);
        BillDTO dto = new BillDTO();
        Bill bill = (Bill) entity;
        dto.setId(((Bill) entity).getId());
        dto.setRegistration(((Bill) entity).getCar().getRegistration());
        dto.setClientId(((Bill) entity).getClient().getId());
        dto.setEmployeeId(((Bill) entity).getEmployee().getId());
        dto.setVat(((Bill) entity).getVat());
        dto.setDiscount(((Bill) entity).getDiscount());
        dto.setTotal(((Bill) entity).getTotal());
        dto.setStatus(((Bill) entity).getStatus().toString());
        dto.setDate(((Bill) entity).getDate());
        dto.setComments(((Bill) entity).getComments());
        List<Integer> taskList = new ArrayList<>();
        List<Task> tasks = ((Bill) entity).getTasks();

        for (Task task : tasks) {
            taskList.add(task.getId());
        }
        dto.setTasks(taskList);
        return dto;
    }

    @Override
    public Bill dtoToEntity(Object entity) throws Exception {
        resetException();
        BillDTO dto = (BillDTO) entity;
        LOGGER.info("dto found: " + dto);
        Bill bill = new Bill();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        bill.setId(id);
        bill.setDate(new Date());
        Car car = carDao.getCarByRegistration(dto.getRegistration());
        if (car == null) throw new InvalidDtoException("car cannot be null");
        LOGGER.info("car found: " + car);
        bill.setCar(car);
        bill.setDiscount(dto.getDiscount());
        bill.setStatus(Status.valueOf(dto.getStatus()));
        List<Task> taskList = new ArrayList<>();
        for (Integer i: dto.getTasks()){
            Task task = (Task) taskDao.getById(i);
            if (task == null) throw new InvalidDtoException("invalid task");
            taskList.add(task);
        }
        bill.setTasks(taskList);
        Client client = (Client) clientDao.getById( dto.getClientId());
        if (client == null) throw new InvalidDtoException("invalid client reference: " + dto.getClientId());
        LOGGER.info("client found: " + client);
        bill.setClient(client);
        bill.setTotal(dto.getTotal());
        bill.setVat(dto.getVat());
        Employee employee = (Employee) employeeDao.getById( dto.getEmployeeId());
        if (employee == null) throw new InvalidDtoException("invalid employee reference: " + dto.getEmployeeId());
        bill.setComments(dto.getComments());
        LOGGER.info("employee found: " + employee);
        bill.setEmployee(employee);
        LOGGER.info("bill transferred: " + bill);
        checkDataInsert(dto);
        return bill;
    }

}
