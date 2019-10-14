package com.autodoc.dao.impl.person.employee;

import com.autodoc.dao.contract.person.employee.EmployeeDao;
import com.autodoc.dao.impl.global.AbstractHibernateDao;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmployeeDaoImpl<T> extends AbstractHibernateDao implements EmployeeDao {
    private Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

    @Override
    public Employee getByLogin(String login) {
        Query query = getCurrentSession().createQuery("From Employee where login= :login");
        query.setParameter("login", login);
        return (Employee) query.getSingleResult();

    }

    @Override
    public Employee getByToken(String token) {
        logger.debug("token: " + token);
        Query query = getCurrentSession().createQuery("From Employee where token= :token");
        query.setParameter("token", token);
        List<Employee> employees = query.getResultList();
        logger.debug("found: " + employees.size());
        if (employees.size() > 0) return employees.get(0);
        return null;
    }

}
