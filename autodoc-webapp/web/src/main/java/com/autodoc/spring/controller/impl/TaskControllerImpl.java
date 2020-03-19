package com.autodoc.spring.controller.impl;

import com.autodoc.business.contract.PieceManager;
import com.autodoc.business.contract.TaskManager;
import com.autodoc.helper.LibraryHelper;
import com.autodoc.model.dtos.tasks.TaskDTO;
import com.autodoc.model.dtos.tasks.TaskForm;
import com.autodoc.model.models.tasks.Task;
import com.autodoc.spring.controller.contract.TaskController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/tasks")
public class TaskControllerImpl extends GlobalController<TaskDTO, Task> implements TaskController {

    private static Logger LOGGER = Logger.getLogger(TaskControllerImpl.class);
    private static final String KEY_WORD = "tasks";
    // @Inject
    //  TaskManager manager;
    PieceManager pieceManager;

    @Override
    String getKeyWord() {
        return KEY_WORD;
    }


    public TaskControllerImpl(LibraryHelper helper, TaskManager manager, PieceManager pieceManager) {
        super(helper);
        this.manager = manager;
        this.pieceManager = pieceManager;
    }


    @GetMapping("")
    public ModelAndView tasks() throws Exception {
        ModelAndView mv = getList();
        getPricePerHour(mv);
        return mv;
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public ModelAndView taskById(@PathVariable Integer id) throws Exception {
        ModelAndView mv = getById(id);
        getPricePerHour(mv);
        return mv;
    }


    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ModelAndView update(@Valid TaskForm taskForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to update member with id " + taskForm.getId());
        String token = helper.getConnectedToken();
        ModelAndView mv = checkAndAddConnectedDetails("tasks/tasks_details");
        mv.addObject("taskForm", new TaskForm());
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                LOGGER.info(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            LOGGER.error("binding has errors: ");
            Task task = (Task) manager.getById(token, taskForm.getId());
            mv.addObject("pieceList", pieceManager.getAll(token));
            mv.addObject("obj", task);
            mv.addObject("form", taskForm);
            mv.addObject("showForm", 0);
            getPricePerHour(mv);
            return mv;
        }
        LOGGER.info("carrying on");
        LOGGER.info("task retrieved: " + taskForm);
        // LOGGER.info("getting the pieces list: "+taskForm.getPieces());
        manager.update(helper.getConnectedToken(), taskForm);
        return new ModelAndView("redirect:" + "/tasks/" + taskForm.getId());

    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id) throws Exception {
        LOGGER.info("trying to delete member with id " + id);
        manager.delete(helper.getConnectedToken(), id);
        return tasks();
    }

    @GetMapping(value = "/new")
    public ModelAndView getCreate() {
        LOGGER.info("getting create form");
        ModelAndView mv = checkAndAddConnectedDetails("tasks/tasks_new");
        mv.addObject("taskForm", new TaskForm());
        mv.addObject("showForm", 1);
        return mv;
    }


    @PostMapping(value = "/new")
    @ResponseBody
    public ModelAndView create(@Valid TaskForm taskForm, BindingResult bindingResult) throws Exception {
        LOGGER.info("trying to create member ");
        ModelAndView mv = checkAndAddConnectedDetails("tasks/tasks_new");
        LOGGER.info("empl: " + taskForm);
        mv.addObject("taskForm", new TaskForm());
        if (bindingResult.hasErrors()) {
            LOGGER.error("binding has errors");
            mv.addObject("taskForm", taskForm);
            mv.addObject("showForm", 1);
            return mv;
        }
        LOGGER.info("task retrieved: " + taskForm);
        manager.add(helper.getConnectedToken(), taskForm);
        return new ModelAndView("redirect:/tasks");
    }

 /*   private TaskDTO convertFormIntoDto(TaskForm taskForm) {
        LOGGER.info("TODO");
        return null;
    }*/


}

