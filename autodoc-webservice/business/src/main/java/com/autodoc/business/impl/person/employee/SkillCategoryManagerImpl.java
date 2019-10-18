package com.autodoc.business.impl.person.employee;

import com.autodoc.business.contract.person.employee.SkillCategoryManager;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.impl.person.employee.SkillCategoryDaoImpl;
import com.autodoc.model.models.person.employee.SkillCategory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SkillCategoryManagerImpl<T, D> extends AbstractGenericManager implements SkillCategoryManager {

    private Logger logger = Logger.getLogger(SkillCategoryManagerImpl.class);
    private SkillCategoryDaoImpl<SkillCategory> skillCategoryDao;

    public SkillCategoryManagerImpl(SkillCategoryDaoImpl<SkillCategory> skillCategoryDao) {
        super(skillCategoryDao);
        this.skillCategoryDao = skillCategoryDao;
    }

    @Override
    public String save(SkillCategory skillCategory) {
        return null;
    }

    @Override
    public Object entityToDto(Object entity) {
        return null;
    }
}
