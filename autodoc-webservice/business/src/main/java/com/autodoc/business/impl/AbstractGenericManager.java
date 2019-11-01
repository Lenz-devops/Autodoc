package com.autodoc.business.impl;

import com.autodoc.business.contract.IGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import com.autodoc.model.models.search.SearchDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Transactional
@Component
public abstract class AbstractGenericManager<T, D> implements IGenericManager<T, D> {

    protected String exception = "";
    private static final Logger LOGGER = Logger.getLogger(AbstractGenericManager.class);
    private IGenericDao<T> dao;

    String[] globalComparator = {"equals", "notEquals"};
    String[] numberComparator = {"smaller", "bigger", "smallerOrEqual", };

    public AbstractGenericManager(IGenericDao dao) {
        this.dao = dao;

    }

    protected void resetException() {
        exception = "";
    }


    public String save(D object) {
        LOGGER.info("trying to save a " + object.getClass());
        try {
            T objectToSave = dtoToEntity((D) object);
            if (!exception.isEmpty()) return exception;
            String feedback = "";
            feedback = Integer.toString(dao.create(objectToSave));
            if (!feedback.equals("0")) {
                return object.getClass().getSimpleName() + " added " + feedback;
            }
            return "issue while saving";
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }

    }


    @Override
    public void checkDataInsert(Object dto) throws Exception {
        LOGGER.info("checking insert data");
    }

    @Override
    public void checkDataUpdate(Object dto) throws Exception {
        LOGGER.info("checking update data");
    }


    @Override
    public D getById(int id) throws Exception {
        if (dao.getById(id) == null) {
            exception = "no record found";
            return null;
        }
        return entityToDto(dao.getById(id));
    }

    @Override
    public D getByName(String name) throws Exception {
        if (dao.getByName(name) == null) {
            exception = "no record found";
            return null;
        }
        return entityToDto(dao.getByName(name));
    }

    @Override
    public List getAll() {
        LOGGER.info("getting ghtm all: ");
        LOGGER.info("trying to find them all");
        LOGGER.debug("dao: " + dao);
        return convertList(dao.getAll());
    }

    private List<D> convertList(List<T> list) {
        List<D> newList = new ArrayList<>();
        for (T obj : list) {
            System.out.println("here");
            Object newObj = (T)entityToDto(obj);
            newList.add((D) newObj);
        }
        return newList;
    }


    @Override
    public D update(Object entity) throws Exception {

        T obj = dtoToEntity((D) entity);
        if (!exception.isEmpty()) {
            throw new Exception(exception);

        }

        D dto = entityToDto((T) dao.update(obj));
        if (!exception.isEmpty()) return null;
        return dto;

    }

    @Override
    public String delete(Object entity) {
        try {
            dao.delete((T) entity);

            return "car deleted";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public String deleteById(int entityId) {

        LOGGER.info("trying to delete " + entityId);
        if (dao.getById(entityId) == null) {
            return "not Found";
        }
        dao.deleteById(entityId);
        return "";
    }

    public List<D> searchByCriteria(List<SearchDTO> dtoList) throws Exception {
        List<Search> search = convertDtoIntoSearch(dtoList);
        System.out.println("trying to get it: "+dtoList.get(0));
        return convertList(dao.getByCriteria(search));
    }

    private List<Search> convertDtoIntoSearch(List<SearchDTO> dtoList) throws Exception {
        Map<String, SearchType> authorizedList=dao.getSearchField();
        List<Search> searchList = new ArrayList<>();
        for (SearchDTO dto:dtoList){
            String field = dto.getFieldName().toUpperCase();
            String compare = dto.getCompare().toUpperCase();
            String value = dto.getValue().toUpperCase();
            dto.setFieldName(field);
            if (!authorizedList.containsKey(field))throw new Exception(field+" is an invalid search criteria");
            String type = authorizedList.get(field).toString().toUpperCase();
            if(!isCompareCriteria(type, dto))throw new Exception(compare+" is invalid or can't be used with "+field);
            if(type.equals("INTEGER")) {
                if(!IsValidNumber(value))throw new Exception(value+" is not a valid number");
            }
            if(type.equals("DATE"))checkDateValue(value);
            if(type.equals("STRING"))value = checkAndAdaptValue(compare, value);
            Search search = new Search(field, compare, value);
            search.setCompare(replaceCompareValueWithSqlCompare(type, compare));
            searchList.add(search);
        }
        return searchList;
    }

    private String checkAndAdaptValue(String compare, String value) throws Exception {
        if (value==null || value.isEmpty())throw new Exception("value cannot be null");
        String[] containsValues={"CONTAINS", "DOESNOTCONTAIN"};
        if (Arrays.asList(containsValues).contains(compare))value="%"+value+"%";
        return value;

    }

    private String replaceCompareValueWithSqlCompare(String type, String compare) throws Exception {
        System.out.println("compare: "+compare);
        for (SearchType searchType: SearchType.values()){
            if (searchType.name().equals(type)){
                for (String[] str:searchType.getValues()) {
                    if (str[0].equalsIgnoreCase(compare)) {
                        System.out.println("replacing: "+str[0]+" with "+str[1]);
                        return str[1];
                    }
                }
            }
        }
        throw new Exception("something went wrong with replacing the compare");
    }

    boolean isCompareCriteria(String type, SearchDTO dto) {
        System.out.println("type: "+type);
        System.out.println("dto: "+dto);
        int found=0;
        for (SearchType searchType: SearchType.values()){
            if (searchType.name().equals(type)){
                for (String[] str:searchType.getValues()) {
                    if (str[0].equalsIgnoreCase(dto.getCompare())) {
                        dto.setCompare(str[1]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDateValue(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("invalid date: "+dateStr);
            return false;
        }
        return true;
    }

    private boolean IsValidNumber(String value) {
        System.out.println("test");
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.println("oh");
            return false;
        }
        return true;
    }




}
