package com.autodoc.business.impl.authentication;

import com.autodoc.business.contract.person.employee.EmployeeManager;
import com.autodoc.model.models.person.employee.Employee;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Named;
import java.util.ArrayList;

@Named
public class JwtConnect implements UserDetailsService {
    private Logger logger = Logger.getLogger(JwtConnect.class);
    private EmployeeManager employeeManager;

    public JwtConnect(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        //User authentication management
        Employee employee = employeeManager.getEmployeeByLogin(login);
        logger.debug("");
        if (employee != null) {
            logger.debug("found: " + login);
            // TODO
            // create token mgt entity and token generation config
            if (employee.getLogin().equals(login)) {
                logger.debug("new password: " + new BCryptPasswordEncoder().encode("abc123").toString());
                // return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                //User user = new User(login, "$2a$10$uY/HyJBjWPp9DXAyuEGUJu2wGzldUhkTu7CUPuaZeCjoo3Ig3CWn2",
                User user = new User(login, employee.getPassword(),
                        new ArrayList<>());
                logger.debug("user: " + user);
                logger.debug("user: " + user);
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + login);
    }

    public void checkToken(String token) {
        //User authentication management
        Employee employee = employeeManager.getEmployeeByToken(token);
        logger.debug("employee: " + employee);
        if (employee != null) {
            if (tokenIsExpired(token)) {
                throw new UsernameNotFoundException("Token expired");
            }
        }
        throw new UsernameNotFoundException("Invalid token: " + token);
    }

    private boolean tokenIsExpired(String token) {
        return false;
    }

}
