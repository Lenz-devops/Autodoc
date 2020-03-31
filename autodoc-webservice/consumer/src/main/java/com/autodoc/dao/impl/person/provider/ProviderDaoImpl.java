package com.autodoc.dao.impl.person.provider;

import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.person.provider.Provider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProviderDaoImpl<T> extends AbstractHibernateDao implements ProviderDao {
    private static final Logger LOGGER = Logger.getLogger(ProviderDaoImpl.class);

    public ProviderDaoImpl() {
        this.setClazz(Provider.class);
    }

    public Map<String, SearchType> getSearchField() {

        return Provider.SEARCH_FIELD;
    }

}
