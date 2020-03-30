package com.autodoc.business.impl;


import com.autodoc.business.contract.TaskManager;
import com.autodoc.contract.TaskService;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.dtos.tasks.TaskForm;
import com.autodoc.model.models.tasks.Task;
import org.apache.log4j.Logger;

import javax.inject.Named;

@Named
public class TaskManagerImpl extends GlobalManagerImpl<Task, TaskDTO> implements TaskManager {

    private static final Logger LOGGER = Logger.getLogger(TaskManagerImpl.class);
    public Task task;
    private TaskService service;

    public TaskManagerImpl(TaskService service) {
        super(service);
        this.service = service;
    }

    public Task dtoToEntity(String token, Object obj) {

        TaskDTO dto = (TaskDTO) obj;
        LOGGER.info("dto: " + dto);
        Task task = new Task();
        int id = dto.getId();
        LOGGER.info("id: " + id);
        task.setId(id);
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setEstimatedTime(dto.getEstimatedTime());
        // String templateValue = dto.getTemplate();
       /* if (templateValue.equalsIgnoreCase("true") || templateValue.equalsIgnoreCase("false"))
            task.setTemplate(Boolean.valueOf(templateValue));*/
        LOGGER.info("task transferred: " + task);

        return task;
    }

    public void update(String token, Object obj) {
        LOGGER.info("updating task: " + obj);
        TaskDTO taskDto = formToDto(obj, token);
        //TaskDTO taskToUpdate = (TaskDTO) service.getById(token, taskDto.getId());
        /*if (taskToUpdate.getTemplate().equalsIgnoreCase("true")) {
            LOGGER.info("updating a template: " + taskDto);
            service.updateTemplate(token, taskDto);
        } else {
        }*/
        LOGGER.info("regular update: " + taskDto);
        service.update(token, taskDto);
    }


    public TaskDTO formToDto(Object obj, String token) {
        LOGGER.info("stuff to update: " + obj);
        TaskForm form = (TaskForm) obj;
        LOGGER.info("dto: " + form);
        TaskDTO dto = new TaskDTO();
        dto.setId(form.getId());
        dto.setName(form.getName());
        dto.setDescription(form.getDescription());
        dto.setEstimatedTime(form.getEstimatedTime());
        // String templateValue = String.valueOf(form.isTemplate());
      /*  if (templateValue.equalsIgnoreCase("true") || templateValue.equalsIgnoreCase("false"))
            dto.setTemplate(templateValue);*/
        LOGGER.info("task transferred: " + dto);
        return dto;
    }


  /*  @Override
    public List<Task> getTemplates(String token) throws Exception {
        List<Task> templates = new ArrayList<>();
        for (Object obj : service.getAll(token)) {
            Task task = dtoToEntity(token, obj);
            if (task.isTemplate()) templates.add(task);
        }
        return templates;
    }*/
}
