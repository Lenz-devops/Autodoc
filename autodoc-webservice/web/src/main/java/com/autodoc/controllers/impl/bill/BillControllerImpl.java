package com.autodoc.controllers.impl.bill;


import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.controllers.contract.bill.BillController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.bill.Bill;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bills")
public class BillControllerImpl extends GlobalControllerImpl<Bill, BillDTO> implements BillController {
    private static final Logger LOGGER = Logger.getLogger(BillControllerImpl.class);
    private BillManager billManager;

    private GsonConverter converter;

    public BillControllerImpl(BillManager billManager) {
        super(billManager);
        if (converter == null) converter = new GsonConverter();
        this.billManager = billManager;
    }

/*
    public ResponseEntity getByName(String name) throws Exception {
        return null;
    }*/
}
