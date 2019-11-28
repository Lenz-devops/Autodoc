package com.autodoc.business.impl;

import com.autodoc.business.contract.GlobalManager;
import com.autodoc.contract.GlobalService;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class GlobalManagerImpl<T, D> implements GlobalManager {
    private static Logger LOGGER = Logger.getLogger(GlobalManagerImpl.class);
    GlobalService service;

    public GlobalManagerImpl(GlobalService service) {
        this.service = service;
    }

    public T getById(String token, int id){
        System.out.println("getting by id");
        System.out.println(service);
        D obj = (D)  service.getById(token, id);
        System.out.println("className: "+obj.getClass().getName());
        T cc = dtoToEntity(token, obj);
        LOGGER.info("object: " + cc);
        System.out.println("className: "+cc.getClass().getName());
        return dtoToEntity(token, obj);
    }

    public T getByName(String token, String name){

        D obj = (D) service.getByName(token, name);
        return dtoToEntity(token, obj);
    }

    public T dtoToEntity(String token, Object obj) {
        LOGGER.info("converting into entito");
        return null;
    }

    public List<T> getAll(String token){

        return (List<T>) service.getAll(token);
    }

    public void add(Object obj){

    }

    public void update(Object obj){

    }

    public void delete(int id){

    }

}
