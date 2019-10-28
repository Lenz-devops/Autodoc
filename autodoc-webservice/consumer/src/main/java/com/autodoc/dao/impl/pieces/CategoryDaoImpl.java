package com.autodoc.dao.impl.pieces;

import com.autodoc.dao.contract.pieces.CategoryDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.pieces.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CategoryDaoImpl<T> extends AbstractHibernateDao implements CategoryDao {
    private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);


    public CategoryDaoImpl() {
        LOGGER.debug("creating manuf dao");
        this.setClazz(Category.class);
    }


}
