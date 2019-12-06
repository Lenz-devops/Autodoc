/*
package com.autodoc.controllers.impl.tasks;


import com.autodoc.business.contract.tasks.TemplateTaskManager;
import com.autodoc.controllers.contract.tasks.TemplateSubTaskController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.GlobalControllerImpl;
import com.autodoc.model.dtos.tasks.TemplateTaskDTO;
import com.autodoc.model.models.tasks.TemplateTask;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templateTasks")
public class TemplateTaskControllerImpl extends GlobalControllerImpl<TemplateTask, TemplateTaskDTO> implements TemplateSubTaskController {
    private final static Logger LOGGER = Logger.getLogger(TemplateTaskControllerImpl.class);
    private TemplateTaskManager manager;
    private GsonConverter converter;

    public TemplateTaskControllerImpl(TemplateTaskManager manager) {
        super(manager);
        converter = new GsonConverter();
        this.manager = manager;
    }


    @Override
    public ResponseEntity getByName(String name) {
        return null;
    }
}
*/
