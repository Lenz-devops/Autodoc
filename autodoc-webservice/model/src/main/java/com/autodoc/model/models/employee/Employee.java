package com.autodoc.model.models.employee;

import com.autodoc.model.enums.Role;
import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "employee")
@Setter
@Getter
public class Employee extends Person {


    public static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Bill> bills;
    @NotNull
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    /* @ManyToMany(mappedBy = "employees", cascade = CascadeType.REMOVE)
     private List<SubTask> subTasks;*/
    @NotNull
    private Date startDate;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private String token;


    /*  @ManyToMany(mappedBy = "employees", cascade = CascadeType.REMOVE)
      private List<Skill> skills;
  */
    private Date lastConnection;
    private Date tokenExpiration;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber, @NotNull List<Role> roles, @NotNull Date startDate, @NotNull String login, @NotNull String password) {
        super(firstName, lastName, phoneNumber);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.password = password;
    }

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("ID", SearchType.INTEGER);
        result.put("firstname", SearchType.STRING);
        result.put("lastname", SearchType.STRING);
        result.put("login", SearchType.STRING);
        result.put("startDate", SearchType.DATE);
        return Collections.unmodifiableMap(result);
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", startDate=" + startDate +
                ", lastConnection=" + lastConnection +
                ", tokenExpiration=" + tokenExpiration +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
