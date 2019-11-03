package com.autodoc.dao.impl.car;

import com.autodoc.dao.contract.car.ManufacturerDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.car.Manufacturer;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ManufacturerDaoImpl<T> extends AbstractHibernateDao implements ManufacturerDao {
    private static final Logger LOGGER = Logger.getLogger(ManufacturerDaoImpl.class);
    private Class cl = Manufacturer.class;

    public ManufacturerDaoImpl() {
        this.setClazz(Manufacturer.class);
    }


    @Override
    public Manufacturer getByName(String name) {
        Query query = getCurrentSession().createQuery("From Manufacturer where name= :name", cl);
        query.setParameter("name", name);
        LOGGER.debug("in dao");
        List<Manufacturer> result = query.getResultList();
        if (!result.isEmpty()) {
            System.out.println("result: " + result.get(0).toString());
            return result.get(0);
        }

        LOGGER.debug("here");
        return null;
    }


    public Map<String, SearchType> getSearchField() {

        return  Manufacturer.SEARCH_FIELD;
    }

}
