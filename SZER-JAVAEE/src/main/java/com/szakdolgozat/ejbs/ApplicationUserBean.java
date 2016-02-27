package com.szakdolgozat.ejbs;

import com.szakdolgozat.dto.LoginDTO;
import com.szakdolgozat.entities.person.ApplicationUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by Ákos on 2016.02.27..
 */
@Stateless
public class ApplicationUserBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

    public void doRegistration(ApplicationUser newUser){
        entityManager.persist(newUser);
    }

    public ApplicationUser doLogin(LoginDTO loginDTO){
        ApplicationUser userFromDB;

        TypedQuery<ApplicationUser> loginQuery;
        loginQuery = entityManager.createQuery("SELECT au FROM ApplicationUser au" +
                " WHERE au.username=:un AND au.password=:p", ApplicationUser.class);
        loginQuery.setParameter("un", loginDTO.getUsername());
        loginQuery.setParameter("p", loginDTO.getPassword());

        try{
            userFromDB = loginQuery.getSingleResult();
        }
        catch(Exception e){
            userFromDB = null;
        }

        return userFromDB;
    }
}
