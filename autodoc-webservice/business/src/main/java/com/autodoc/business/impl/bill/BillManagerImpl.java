package com.autodoc.business.impl.bill;

import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.bill.BillDaoImpl;
import com.autodoc.model.models.bill.Bill;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class BillManagerImpl extends AbstractGenericManager implements BillManager {
    private BillDaoImpl billDao;
    private Logger logger = Logger.getLogger(BillManagerImpl.class);


    public BillManagerImpl(BillDaoImpl billDao) {
        super(billDao);
        this.billDao = billDao;

    }


    @Override
    public String save(Object bill) {
        logger.debug("trying to save a car");
        logger.info("trying to save a like: " + bill);
        billDao.create(bill);
        return "car created";
    }

    @Override
    public List<Bill> getAll() {
        return billDao.getAll();
    }

    @Override
    public Object entityToDto(Object entity) {
        return null;
    }

    @Override
    public Object dtoToEntity(Object entity) {
        return null;
    }

}
