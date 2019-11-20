package com.autodoc.impl;

import com.autodoc.contract.GlobalService;
import com.autodoc.model.Car;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GlobalServiceImpl<T> implements GlobalService {

    private static final String BASE_URL = "http://localhost:8087/autodoc/";
    private static final Logger LOGGER = Logger.getLogger(GlobalServiceImpl.class);
    RestTemplate restTemplate;
    HttpEntity<?> request;

    public String getClassName() {

        return getObjectClass().getSimpleName().toLowerCase()+"s";
    }


    Class getObjectClass(){
        return null;
    }

    public GlobalServiceImpl() {

    }

    void setupHeader(String token){
        System.out.println("setting up");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        this.restTemplate = new RestTemplate();

        this.request = new HttpEntity<>( headers);
    }


    @Override
    public Object getById(String token, int id) {
        LOGGER.info("trying to get car by id");
        setupHeader(token);

        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("id: " + id);
            String className = getClassName();
            String url = BASE_URL+className+"/"+id;
            LOGGER.info("url: "+url);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, getObjectClass());
            LOGGER.info("stop");
            System.out.println("req: "+request);
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public Object getByName(String token, String name) {
        return null;
    }

    @Override
    public List getAll(String token) {
        return null;
    }

    @Override
    public void create(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List getByCriteria(String token, Map criteria) {
        return null;
    }
}
