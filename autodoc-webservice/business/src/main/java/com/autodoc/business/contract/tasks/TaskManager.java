package com.autodoc.business.contract.tasks;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.models.tasks.Task;

import java.util.List;

public interface TaskManager extends IGenericManager {

    List<TaskDTO> getTemplates();

    boolean updateTemplate(TaskDTO taskDTO) throws Exception;

    boolean deleteTemplateById(int id) throws Exception;

    Task createFromTemplate(int id) throws Exception;


}
