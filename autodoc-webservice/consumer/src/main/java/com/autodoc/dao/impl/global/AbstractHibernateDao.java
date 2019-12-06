package com.autodoc.dao.impl.global;

import com.autodoc.model.enums.Compare;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.search.Search;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.expression.ParseException;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public abstract class AbstractHibernateDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractHibernateDao.class);
    @Inject
    SessionFactory sessionFactory;
    int maxCharacters = 22;
    private Class<T> clazz;
    private String dateFormat;

    public static boolean isValidNumber(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class clazzToSet) {
        this.clazz = clazzToSet;

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T getById(int id) {
        return getCurrentSession().get(clazz, id);
    }

    //@Override
    public T getByName(String name) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName() + "where name = :name");
        query.setParameter(name, name);
        if (query.getResultList().isEmpty()) return null;
        return (T) query.getResultList().get(0);
    }

    public List<T> getAll() {
        System.out.println("in the dao: " + clazz.getName());
        LOGGER.debug("class: " + clazz.getName());
        LOGGER.debug("getting all");
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }

    public int create(T entity) {
        System.out.println("trying to create");
        try {
            return (Integer) getCurrentSession().save(entity);
        } catch (Exception e) {
            System.out.println("error while creating");
            return 0;
        }
    }

    public boolean delete(T entity) {
        System.out.println("I want to delete: " + entity);
        getCurrentSession().delete(entity);
        return true;

    }

    public boolean update(T entity) {
        System.out.println("updating from dao: " + entity);
        T obj = (T) getCurrentSession().merge(entity);
        return obj != null;
    }

    public boolean deleteById(int entityId) {
        System.out.println("trying to delete element with id: " + entityId);
        T entity = getCurrentSession().get(clazz, entityId);
        System.out.println(entity);
        if (entity == null) {
            System.out.println("entity not found");
            return false;
        }
        delete(entity);
        return getById(entityId) == null;
    }

    protected Session getCurrentSession() {
        LOGGER.debug("session: " + sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession();
    }

    public List<T> getByCriteria(List<Search> search) throws Exception {
        if (search == null) throw new Exception("no search criteria provided");
        if (getSearchField() == null) throw new Exception("no search criteria available for that entity");
        String request = buildCriteriaRequest(search);
        System.out.println("req: " + request);
        Query query = sessionFactory.getCurrentSession().createQuery(request);
        return query.getResultList();
    }

    protected String buildCriteriaRequest(List<Search> searchList) throws Exception {

        StringBuilder sb = new StringBuilder();
        String init = "from " + clazz.getSimpleName();
        sb.append(init);
        // Search search = searchList.get(0);
        for (Search search : searchList) {
            if (sb.toString().equals(init)) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(search.getFieldName() + " " + search.getCompare() + " '" + search.getValue() + "'");

        }
        //sb.append(" where "+search.getFieldName()+" "+search.getCompare()+" '"+search.getValue()+"'");
        return sb.toString();



       /* StringBuilder builder = new StringBuilder();
        String init="from "+clazz.getSimpleName();
        Map<String, SearchType> authorizedSearchFieldList = getSearchField();

        if (authorizedSearchFieldList == null) return null;

        builder.append(init);

        for (Search s : searchList) {
            if (s.getValue()==null||s.getValue().isEmpty())throw new Exception(s.getFieldName()+"cannot be null");
            if(s.getValue().length() > maxCharacters)throw new Exception(s.getValue()+"cannot is more than the authorized "+maxCharacters);
            s.setValue(s.getValue().toUpperCase());
            checkIfInvalidField(authorizedSearchFieldList, s);
            checkIfInvalidValue(s.getCompare(), s.getValue());
            System.out.println("old val: "+s.getValue());
            if(s.getCompare() ==Compare.STRINGCONTAINS || s.getCompare()==Compare.STRINGDOESNOTCONTAIN)s.setValue("\'%"+s.getValue()+"%\'");
            System.out.println("new val: "+s.getValue());
            if (builder.toString().equals(init)){
                builder.append(" where ");
            }else {
                builder.append(" and ");
            }
            builder.append(s.getFieldName()+s.getCompare().getQueryValue()+" "+s.getValue()+" ");
        }
        System.out.println("query: "+builder.toString());
        LOGGER.info("query build: "+builder.toString());

        return builder.toString();*/
        //return null;
    }

    private void checkIfInvalidValue(Compare compare, String value) throws Exception {
        String type = compare.getType();
        if (type.equals("Integer")) {
            if (!isValidNumber(value)) throw new Exception(value + " is not a number");
        } else if (type.equals("Date")) {
            if (!isValidDate(value)) throw new Exception(value + "is not a date");
        }
    }

    private void checkIfInvalidField(Map<String, SearchType> authorizedSearchFieldList, Search s) throws Exception {
        if (!authorizedSearchFieldList.containsKey(s.getFieldName())) {
            throw new Exception("invalid criteria: " + s.getFieldName());
        }
    }

    public boolean isValidDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        } catch (java.text.ParseException e) {
            return false;
        }
        return true;
    }


    public Map<String, SearchType> getSearchField() {

        return null;
    }

}
